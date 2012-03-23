CREATE TABLE clientInstances (
    instanceId                  INTEGER PRIMARY KEY AUTOINCREMENT,
    clusterStartTime            bigint NOT NULL,
    clusterLeaderAddress        varchar(64) NOT NULL,
    applicationName             varchar(32) NOT NULL,
    subApplicationName          varchar(32)
);

CREATE TABLE clientConnectionStats (
    instanceId                  INTEGER PRIMARY KEY AUTOINCREMENT,
    tsEvent                     bigint NOT NULL,
    hostname                    varchar(64) NOT NULL,
    connectionId                bigint NOT NULL,
    serverHostId                bigint NOT NULL,
    serverHostname              varchar(64) NOT NULL,
    serverConnectionId          bigint NOT NULL,
    numInvocations              bigint NOT NULL,
    numAborts                   bigint NOT NULL,
    numFailures                 bigint NOT NULL,
    numBytesRead                bigint NOT NULL,
    numMessagesRead             bigint NOT NULL,
    numBytesWritten             bigint NOT NULL,
    numMessagesWritten          bigint NOT NULL
);

CREATE TABLE clientProcedureStats (
    instanceId                  INTEGER PRIMARY KEY AUTOINCREMENT,
    tsEvent                     bigint NOT NULL,
    hostname                    varchar(64) NOT NULL,
    connectionId                bigint NOT NULL,
    serverHostId                bigint NOT NULL,
    serverHostname              varchar(64) NOT NULL,
    serverConnectionId          bigint NOT NULL,
    procedureName               varchar(64) NOT NULL,
    roundtripAvg                int NOT NULL,
    roundtripMin                int NOT NULL,
    roundtripMax                int NOT NULL,
    clusterRoundtripAvg         bigint NOT NULL,
    clusterRoundtripMin         bigint NOT NULL,
    clusterRoundtripMax         bigint NOT NULL,
    numInvocations              bigint NOT NULL,
    numAborts                   bigint NOT NULL,
    numFailures                 bigint NOT NULL,
    numRestarts                 bigint NOT NULL
);
