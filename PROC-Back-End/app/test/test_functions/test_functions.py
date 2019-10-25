import pytest
from app.main.functions import check_required_keys, calculate_order_price


@pytest.mark.parametrize('keys,element,result', [
    (
        ('key1', 'key2', 'key3', 'key4'),
        {
            'key1': 1,
            'key2': 2,
            'key3': 3,
            'key4': 4
        },
        True
    ),
    (
        ('key1', 'key2', 'key3', 'key4'),
        {
            'key1': 1,
            'key2': 2,
            'key3': 3,
        },
        False
    ),
    (
        (),
        {
            'key1': 1,
            'key2': 2,
            'key3': 3,
        },
        True
    ),
    (
        ('key1', 'key2', 'key3', 'key4'),
        {},
        False
    )

])
def test_check_required_keys(keys, element, result):
    assert check_required_keys(keys, element) == result


class DummyOrder:

    def __init__(self, price):
        class Size:
            def __init__(self, price):
                self.price = price

        self.size = Size(price)


class DummyIngredient:

    def __init__(self, price):
        self.price = price


@pytest.mark.parametrize('order,ingredients,total', [
    (
        DummyOrder(10),
        [DummyIngredient(3.4), DummyIngredient(3.5)],
        16.9
    ),
    (
        DummyOrder(10),
        [],
        10
    ),
    (
        DummyOrder(15.3),
        [DummyIngredient(3.4), DummyIngredient(3.5)],
        22.2
    )
])
def test_calculate_order_price(order, ingredients, total):
    assert calculate_order_price(order, ingredients) == total
