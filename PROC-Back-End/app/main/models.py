from .plugins import db
from datetime import datetime

__all__ = [
    'Order',
    'Ingredient',
    'Size',
    'OrderDetail'
]


class Order(db.Model):
    _id = db.Column(db.Integer, primary_key=True)
    client_name = db.Column(db.String(80))
    client_dni = db.Column(db.String(10))
    client_address = db.Column(db.String(128))
    client_phone = db.Column(db.String(15))
    date = db.Column(db.DateTime, default=datetime.utcnow)
    total_price = db.Column(db.Float)
    size_id = db.Column(db.Integer, db.ForeignKey('size._id'))

    size = db.relationship('Size', backref=db.backref('size'))
    detail = db.relationship('OrderDetail', backref=db.backref('order_detail'))


class Ingredient(db.Model):
    _id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80), nullable=False)
    price = db.Column(db.Float, nullable=False)


class Size(db.Model):
    _id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80), nullable=False)
    price = db.Column(db.Float, nullable=False)


class OrderDetail(db.Model):
    _id = db.Column(db.Integer, primary_key=True)
    ingredient_price = db.Column(db.Float)
    order_id = db.Column(db.Integer, db.ForeignKey('order._id'))
    ingredient_id = db.Column(db.Integer, db.ForeignKey('ingredient._id'))
    ingredient = db.relationship('Ingredient', backref=db.backref('ingredient'))
