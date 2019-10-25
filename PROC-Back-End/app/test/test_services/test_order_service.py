import pytest


@pytest.fixture
def order_uri():
    return '/api/order'


@pytest.fixture
def ingredient_uri():
    return '/api/ingredient'


@pytest.fixture
def size_uri():
    return '/api/size'


@pytest.fixture
def dummy_ingredients():
    return [
        {
            'name': 'test_ingredient1',
            'price': 0.5
        },
        {
            'name': 'test_ingredient2',
            'price': 0.6
        },
        {
            'name': 'test_ingredient3',
            'price': 0.7
        }
    ]


@pytest.fixture
def dummy_sizes():
    return [
        {
            'name': 'test_size1',
            'price': 10.0
        },
        {
            'name': 'test_size2',
            'price': 20.0
        },
        {
            'name': 'test_size3',
            'price': 30.0
        }
    ]


# @pytest.fixture
# def dummy_orders():
#     return


@pytest.mark.parametrize('order,status_code,total_price', [
    (
        {
            'client_address': 'Conocoto',
            'client_dni': '1724561921',
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': ['1', '2', '3'],
            'size': 1
        }, '201', 11.8
    ),
    (
        {
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': ['1', '2', '3'],
            'size': 1
        }, '400', None
    ),
    (
        {}, '400', None
    ),
    (
        {
            'client_address': 'Conocoto',
            'client_dni': '1724561921',
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': [1, 2, 3],
            'size': 1
        }, '201', 11.8
    ),
    (
        {
            'client_address': 'Conocoto',
            'client_dni': '1724561921',
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': [1, 2, 3],
            'size': 8
        }, '400', None
    ),
    (
        {
            'client_address': 'Conocoto',
            'client_dni': '1724561921',
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': [1, 2],
            'size': '2'
        }, '201', 21.1
    ),
    (
        {
            'client_address': 'Conocoto',
            'client_dni': '1724561921',
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': [1, 2, 9],
            'size': '1'
        }, '400', None
    ),
    (
        {
            'client_address': 'Conocoto',
            'client_dni': '1724561921',
            'client_name': 'Wladymir',
            'client_phone': '0983147655',
            'ingredients': [],
            'size': '3'
        }, '201', 30.0
    )
])
def test_create_order(client, order_uri, ingredient_uri, size_uri, order, status_code, dummy_ingredients, dummy_sizes, total_price):
    for size in dummy_sizes:
        client.post(size_uri, json=size)

    for ingredient in dummy_ingredients:
        client.post(ingredient_uri, json=ingredient)

    response = client.post(order_uri, json=order)

    pytest.assume(response.status.startswith(status_code))
    if status_code != '400':
        pytest.assume(response.json.get('_id') is not None)
        pytest.assume(response.json.get('client_address') == order.get('client_address'))
        pytest.assume(response.json.get('client_name') == order.get('client_name'))
        pytest.assume(response.json.get('client_dni') == order.get('client_dni'))
        pytest.assume(response.json.get('client_name') == order.get('client_name'))
        pytest.assume(len(response.json.get('detail')) == len(order.get('ingredients')))
        pytest.assume(response.json.get('size').get('_id') == int(order.get('size')))
        pytest.assume(response.json.get('date') is not None)
        # pytest.assume(response.json.get('total_price') == total_price)


@pytest.mark.parametrize('orders,status_code', [
    (
        [
            {
                'client_address': 'Conocoto',
                'client_dni': '1724561921',
                'client_name': 'Wladymir',
                'client_phone': '0983147655',
                'ingredients': ['1', '2', '3'],
                'size': 1
            },
            {
                'client_address': 'Conocoto',
                'client_dni': '1724561921',
                'client_name': 'Wladymir',
                'client_phone': '0983147655',
                'ingredients': ['1', '2'],
                'size': 2
            },
            {
                'client_address': 'Conocoto',
                'client_dni': '1724561921',
                'client_name': 'Wladymir',
                'client_phone': '0983147655',
                'ingredients': ['1'],
                'size': 3
            },
        ], '200'
    ),
    ([], '200')
])
def test_get_orders(client, ingredient_uri, order_uri, size_uri, dummy_ingredients, dummy_sizes, orders, status_code):
    for size in dummy_sizes:
        client.post(size_uri, json=size)

    for ingredient in dummy_ingredients:
        client.post(ingredient_uri, json=ingredient)

    for order in orders:
        client.post(order_uri, json=order)

    response = client.get(order_uri)

    pytest.assume(response.status.startswith(status_code))
    pytest.assume(isinstance(response.json, list))
    pytest.assume(len(response.json) == len(orders))

    for i in range(len(response.json)):
        pytest.assume(response.json[i].get('_id') is not None)
        pytest.assume(response.json[i].get('client_address') == orders[i].get('client_address'))
        pytest.assume(response.json[i].get('client_name') == orders[i].get('client_name'))
        pytest.assume(response.json[i].get('client_dni') == orders[i].get('client_dni'))
        pytest.assume(response.json[i].get('client_name') == orders[i].get('client_name'))
        pytest.assume(len(response.json[i].get('detail')) == len(orders[i].get('ingredients')))
        pytest.assume(response.json[i].get('size').get('_id') == int(orders[i].get('size')))
        pytest.assume(response.json[i].get('date') is not None)
        pytest.assume(response.json[i].get('total_price') is not None)


@pytest.mark.parametrize('orders,status_code', [
    (
        [
            {
                'client_address': 'Conocoto',
                'client_dni': '1724561921',
                'client_name': 'Wladymir',
                'client_phone': '0983147655',
                'ingredients': ['1', '2', '3'],
                'size': 1
            },
            {
                'client_address': 'Conocoto',
                'client_dni': '1724561921',
                'client_name': 'Wladymir',
                'client_phone': '0983147655',
                'ingredients': ['1', '2'],
                'size': 2
            },
            {
                'client_address': 'Conocoto',
                'client_dni': '1724561921',
                'client_name': 'Wladymir',
                'client_phone': '0983147655',
                'ingredients': ['1'],
                'size': 3
            },
        ], '200'
    ),
    ([], '404')
])
def test_get_order_by_id(client, ingredient_uri, order_uri, size_uri, dummy_ingredients, dummy_sizes, orders, status_code):
    for size in dummy_sizes:
        client.post(size_uri, json=size)

    for ingredient in dummy_ingredients:
        client.post(ingredient_uri, json=ingredient)

    for order in orders:
        client.post(order_uri, json=order)

    if len(orders) == 0:
        assert client.get('{}/id/{}'.format(order_uri, 1)).status.startswith(status_code)

    else:
        for i in range(len(orders)):
            response = client.get('{}/id/{}'.format(order_uri, i+1))
            pytest.assume(response.json.get('_id') is not None)
            pytest.assume(response.json.get('client_address') == orders[i].get('client_address'))
            pytest.assume(response.json.get('client_name') == orders[i].get('client_name'))
            pytest.assume(response.json.get('client_dni') == orders[i].get('client_dni'))
            pytest.assume(response.json.get('client_name') == orders[i].get('client_name'))
            pytest.assume(len(response.json.get('detail')) == len(orders[i].get('ingredients')))
            pytest.assume(response.json.get('size').get('_id') == int(orders[i].get('size')))
            pytest.assume(response.json.get('date') is not None)
            pytest.assume(response.json.get('total_price') is not None)
