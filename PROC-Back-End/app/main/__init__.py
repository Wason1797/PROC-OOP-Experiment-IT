from ..settings import Config


def create_app(config_class):
    from flask import Flask
    flask_app = Flask(__name__)
    flask_app.config.from_object(config_class)
    return flask_app


def register_blueprints(flask_app):
    from .service import urls
    flask_app.register_blueprint(urls, url_prefix='/api')


def register_plugins(flask_app):
    from .plugins import db, ma
    db.init_app(flask_app)
    ma.init_app(flask_app)


def cors_app(flask_app):
    from flask_cors import CORS
    CORS(flask_app)


def configure_app(config_class):
    flask_app = create_app(config_class)
    register_blueprints(flask_app)
    register_plugins(flask_app)
    cors_app(flask_app)

    return flask_app


flask_app = configure_app(Config)
