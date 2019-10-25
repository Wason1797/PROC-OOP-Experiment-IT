# import pytest
import pytest


@pytest.fixture
def size_uri():
    return '/api/size'


@pytest.mark.parametrize('size,status_code', [
    (
        {
            'name': 'test_size',
            'price': 20
        }, '201'
    ),
    (
        {
            'name': 'test_size2',
        }, '400'
    ),
    (
        {}, '400'
    ),
    (
        {
            'name': 'test_size',
            'price': '20'
        }, '201'
    ),
])
def test_post_size(client, size_uri, size, status_code):
    response = client.post(size_uri, json=size)
    pytest.assume(response.status.startswith(status_code))
    if status_code != '400':
        pytest.assume(response.json.get('_id') is not None)
        pytest.assume(response.json.get('name') == size.get('name'))
        pytest.assume(response.json.get('price') == float(size.get('price')))


@pytest.mark.parametrize('size,status_code', [
    (
        {
            'name': 'test_size1',
            'price': 20
        }, '400'
    ),
    (
        {
            '_id': 1,
            'name': 'test_size2',
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
def test_update_size(client, size_uri, size, status_code):

    client.post(size_uri, json={
        'name': 'test_size1',
        'price': 20
    }
    )

    response = client.put(size_uri, json=size)
    print(response.json)
    pytest.assume(response.status.startswith(status_code))
    if status_code != '400':
        pytest.assume(response.json.get('_id') is not None)
        pytest.assume(response.json.get('name') == size.get('name') or response.json.get('name'))
        pytest.assume(response.json.get('price') == float(size.get('price') or response.json.get('price')))


@pytest.mark.parametrize('_id,status_code', [
    (
        1, '200'
    ),
    (
        2, '404'
    )
])
def test_get_size_by_id(client, size_uri, _id, status_code):

    size = {
        'name': 'test_size1',
        'price': 20
    }

    response = client.post(size_uri, json=size)
    response = client.get('{}/id/{}'.format(size_uri, _id))

    pytest.assume(response.status.startswith(status_code))
    if status_code != '404':
        pytest.assume(response.json.get('_id') == _id)
        pytest.assume(response.json.get('name') == size.get('name'))
        pytest.assume(response.json.get('price') == float(size.get('price')))


@pytest.mark.parametrize('sizes,status_code', [
    ([
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
    ], '200'),
    ([], '200')

])
def test_get_sizes(client, size_uri, sizes, status_code):

    for size in sizes:
        client.post(size_uri, json=size)

    response = client.get(size_uri)

    pytest.assume(response.status.startswith(status_code))

    for i in range(len(sizes)):
        pytest.assume(response.json[i].get('_id') is not None)
        pytest.assume(response.json[i].get('name') == sizes[i].get('name'))
        pytest.assume(response.json[i].get('price') == sizes[i].get('price'))
