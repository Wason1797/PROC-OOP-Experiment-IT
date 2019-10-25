
def get_all(Model, Serializer):
    serializer = Serializer(many=True)
    _objects = Model.query.all()
    result = serializer.dump(_objects)
    return result


def calculate_order_price(order, ingredients):
    size_price = order.size.price
    sum(ingredient.price for ingredient in ingredients)
    return round(size_price, 2)+sum(ingredient.price for ingredient in ingredients)


def check_required_keys(keys: tuple, _element: dict):
    return all(_element.get(key) for key in keys)
