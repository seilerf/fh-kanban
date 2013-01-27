# Team project 2013
Build a Swing application to manage a kanban board. This application provides the basic functionality for the kanban process.

## Maven
### Test application
```shell
mvn test
```
### Build application
```shell
mvn install
``` 
### Run application
```shell
mvn exec:java
```

## Requirements

+ Create new kanban boards
+ Open board
+ Save board
+ Save as…
+ Export to html, pdf
+ Export backlog to csv
+ Warn user if modifications have not been saved
+ Preferences dialog for colors, name of board, columns, wips
+ Cos (Fixed date, standard, intagible, expedite)
+ Card properties (uuid, headline, description, size, value, created, started, finished)
+ Validate on wip limits
+ Move cards between columns
+ Full text search backlog and board by headline, description, CoS, size, value
+ Store board as xml
+ Tests for all major parts of the application
+ Edit didalog for card
+ Backlogs displays cards in 3 * n Grid 
+ Backlog can be sorted by creation time (default), headline, value, size