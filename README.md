# Cercli-assessment

Basic tips to run the project:
- Install postgres:
  `brew install postgresql`

- Start postgres service:
  `brew services start postgresql`

- Run postgres locally:
  `psql postgres`

- Create new role for Cercli:
  `CREATE ROLE cercli WITH LOGIN PASSWORD <can be found in persistence.xml>`

- Alter role:
  `ALTER ROLE cercli CREATEDB;`

- Create user cercli:
  `createuser cercli`

- Create DB:
 ` Create database cercli;`

- Grant access to user cercli on DB cercli:
  `GRANT ALL PRIVILEGES ON DATABASE cercli TO cercli;`

- SQL statements to create table and audit trigger can be found in the `SQL` folder

- This is a maven project. Useful maven commands:
  `mvn clean install`

  `mvn dependency:copy-dependencies`
- To run the Main class through bash/terminal:
  `java -cp "target/classes:target/dependency/*" cercli.Main`