Feature: Mongodb Integration

    Integrate library with Mongodb

    Background: Mongo table setup
        Given a Mongo DB with an anime table

    Scenario: Basic CRUD
        Given an anime was inserted into the Mongo DB anime table
        When the anime is updated in the Mongo DB
        Then the change should be persistent in the Mongo DB
        And the anime should be deleatable from the Mongo DB