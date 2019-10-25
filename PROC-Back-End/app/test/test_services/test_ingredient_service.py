import pytest


@pytest.fixture
def ingredient_uri():
    return '/api/ingredient'


@pytest.mark.parametrize('ingredient,status_code', [
    (
        {
            'name': 'test_ingredient',
            'price': 20
        }, '201'
    ),
    (
        {
            'name': 'test_ingredient2',
        }, '400'
    ),
    (
        {}, '400'
    ),
    (
        {
            'name': 'test_ingredient',
            'price': '20'
        }, '201'
    ),
])
def test_post_ingredient(client, ingredient_uri, ingredient, status_code):
    response = client.post(ingredient_uri, json=ingredient)
    pytest.assume(response.status.startswith(status_code))
    if status_code != '400':
        pytest.assume(response.json.get('_id') is not None)
        pytest.assume(response.json.get('name') == ingredient.get('name'))
        pytest.assume(response.json.get('price') == float(ingredient.get('price')))


@pytest.mark.parametrize('ingredient,status_code', [
    (
        {
            'name': 'test_ingredient1',
            'price': 20
        }, '400'
    ),
    (
        {
            '_id': 1,
            'name': 'test_ingredient2',
        }, '200'
    ),
    (
        {
            '_id': 1,
            'price': 30,
        }, '200'
    ),
    ({}, '400')
])
def test_update_ingredient(client, ingredient_uri, ingredient, status_code):

    client.post(ingredient_uri, json={
        'name': 'test_ingredient1',
        'price': 20
    }
    )

    response = client.put(ingredient_uri, json=ingredient)
    print(response.json)
    pytest.assume(response.status.startswith(status_code))
    if status_code != '400':
        pytest.assume(response.json.get('_id') is not None)
        pytest.assume(response.json.get('name') == ingredient.get('name') or response.json.get('name'))
        pytest.assume(response.json.get('price') == float(ingredient.get('price') or response.json.get('price')))


@pytest.mark.parametrize('_id,status_code', [
    (
        1, '200'
    ),
    (
        2, '404'
    )
])
def test_get_ingredient_by_id(client, ingredient_uri, _id, status_code):

    ingredient = {
        'name': 'test_ingredient1',
        'price': 20
    }

    response = client.post(ingredient_uri, json=ingredient)
    response = client.get('{}/id/{}'.format(ingredient_uri, _id))

    pytest.assume(response.status.startswith(status_code))
    if status_code != '404':
        pytest.assume(response.json.get('_id') == _id)
        pytest.assume(response.json.get('name') == ingredient.get('name'))
        pytest.assume(response.json.get('price') == float(ingredient.get('price')))


@pytest.mark.parametrize('ingredients,status_code', [
    ([
        {
            'name': 'test_ingredient1',
            'price': 10.0
        },
        {
            'name': 'test_ingredient2',
            'price': 20.0
        },
        {
            'name': 'test_ingredient3',
            'price': 30.0
        }
    ], '200'),
    ([], '200')

])
def test_get_ingredients(client, ingredient_uri, ingredients, status_code):

    for ingredient in ingredients:
        client.post(ingredient_uri, json=ingredient)

    response = client.get(ingredient_uri)

    pytest.assume(response.status.startswith(status_code))

    for i in range(len(ingredients)):
        pytest.assume(response.json[i].get('_id') is not None)
        pytest.assume(response.json[i].get('name') == ingredients[i].get('name'))
        pytest.assume(response.json[i].get('price') == ingredients[i].get('price'))
