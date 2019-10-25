import pytest
import tempfile
import os


@pytest.fixture
def app():
    from ..main import create_app, register_blueprints
    from ..main.plugins import ma, db
    from app.main.models import Order, OrderDetail, Ingredient, Size

    db_fd, dbpath = tempfile.mkstemp()

    class Config:
        SQLALCHEMY_DATABASE_URI = 'sqlite:///{}'.format(dbpath)
        TESTING = True
        SQLALCHEMY_TRACK_MODIFICATIONS = False

    flask_app = create_app(Config)
    register_blueprints(flask_app)
    flask_app.app_context().push()
    db.init_app(flask_app)
    ma.init_app(flask_app)

    db.create_all()

    yield flask_app

    db.session.remove()
    db.drop_all()
    os.close(db_fd)
    os.remove(dbpath)


@pytest.fixture
def client(app):
    client = app.test_client()
    return client
