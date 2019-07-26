Feature: Sqlite Integration

    Integrate library with Sqlite

    Background: Sqlite table setup
        Given a Sqlite DB with an anime table

    Scenario: Basic CRUD
        Given an anime was inserted into the sqlite DB anime table
        When the anime is updated in the sqlite DB
        Then the change should be persistent in the sqlite DB
        And the anime should be deleatable from the sqlite DB