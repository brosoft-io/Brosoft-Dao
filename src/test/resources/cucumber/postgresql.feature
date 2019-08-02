Feature: Postgresql Integration

    Integrate library with Postgresql

    Background: Postgresql table setup
        Given a Postgresql DB with an anime table

    Scenario: Basic CRUD
        Given an anime was inserted into the Postgresql DB anime table
        When the anime is updated in the Postgresql DB
        Then the change should be persistent in the Postgresql DB
        And the anime should be deleatable from the Postgresql DB