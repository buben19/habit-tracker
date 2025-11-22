-- go back to a DB that always exists
\connect postgres

--------------------------
-- habit-service
--------------------------
CREATE DATABASE habitdb
  WITH ENCODING 'UTF8'
       LC_COLLATE='en_US.utf8'
       LC_CTYPE='en_US.utf8';

CREATE USER habit WITH PASSWORD 'habit';

GRANT ALL PRIVILEGES ON DATABASE habitdb TO habit;

-- Optional: connect to habitdb and adjust defaults
\connect habitdb

-- (not strictly needed, but nice)
ALTER SCHEMA public OWNER TO habit;
GRANT ALL ON SCHEMA public TO habit;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
  GRANT ALL ON TABLES TO habit;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
  GRANT ALL ON SEQUENCES TO habit;
ALTER DEFAULT PRIVILEGES IN SCHEMA public
  GRANT ALL ON FUNCTIONS TO habit;