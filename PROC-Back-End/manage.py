from app.main import flask_app
from app.main.plugins import db
from flask_script import Manager
from flask_migrate import Migrate, MigrateCommand
import pytest


# from app.main.models import Order, OrderDetail, Ingredient, Size

flask_app.app_context().push()

manager = Manager(flask_app)

migrate = Migrate(flask_app, db)

manager.add_command('db', MigrateCommand)


@manager.command
def run():
    flask_app.run()


@manager.command
def test():
    return pytest.main(['-v', './app/test'])


if __name__ == '__main__':
    manager.run()
