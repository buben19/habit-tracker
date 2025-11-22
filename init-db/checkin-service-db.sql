-- go back to a DB that always exists
\connect postgres

--------------------------
-- checkin-service
--------------------------

CREATE DATABASE checkindb
    WITH ENCODING 'UTF8'
    LC_COLLATE='en_US.utf8'
    LC_CTYPE='en_US.utf8';

CREATE USER checkin WITH PASSWORD 'checkin';

GRANT ALL PRIVILEGES ON DATABASE checkindb TO checkin;

\connect checkindb

ALTER SCHEMA public OWNER TO checkin;
GRANT ALL ON SCHEMA public TO checkin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL ON TABLES TO checkin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL ON SEQUENCES TO checkin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL ON FUNCTIONS TO checkin;
