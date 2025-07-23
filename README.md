# Install Cassandra
```shell
brew install cassandra
brew services start cassandra
cqlsh
```

Create KeySpace
```
cqlsh> DESCRIBE KEYSPACES;

system       system_distributed  system_traces  system_virtual_schema
system_auth  system_schema       system_views

cqlsh> CREATE KEYSPACE my_keyspace WITH replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
cqlsh> DESCRIBE KEYSPACES;

my_keyspace  system_auth         system_schema  system_views
system       system_distributed  system_traces  system_virtual_schema
```

Create Table Under KeySpace
```shell
CREATE TABLE person (
    id uuid PRIMARY KEY,
    title text,
    publisher text,
    tags set<text>
);
```

# Run Application
