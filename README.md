SBT Liquibase plugin for SBT 1
====================================

# Instructions for use:
### Step 1: Include the plugin in your build

Add the following to your `project/plugins.sbt`:

    addSbtPlugin("com.permutive" % "sbt-liquibase" % "1.1.0")

### Step 2: Activate sbt-liquibase-plugin in your build

Add the following to your 'build.sbt' ( if you are using build.sbt )

    import com.permutive.sbtliquibase.SbtLiquibase
    enablePlugins(SbtLiquibase)
    liquibaseUsername := ""
    liquibasePassword := ""
    liquibaseDriver   := "com.mysql.jdbc.Driver"
    liquibaseUrl      := "jdbc:mysql://localhost:3306/test_db?createDatabaseIfNotExist=true"

## Settings

|Setting|Description|Example|
|-------|-----------|-------|
|liquibaseUsername|Username for the database. This defaults to blank.|`liquibaseUsername := "your_db_username"`|
|liquibasePassword|Password for the database. This defaults to blank.|`liquibasePassword := "secret"`|
|liqubaseDriver|Database driver classname. There is no default.|`liquibaseDriver := "com.mysql.jdbc.Driver"`|
|liquibaseUrl|Database connection uri. There is no default.|`liquibaseUrl := "jdbc:mysql://localhost:3306/mydb"`|
|liquibaseChangelogCatalog|Default catalog name for the changelog tables. This defaults to None.|`liquibaseChangelogCatalog := Some("my_catalog")`|
|liquibaseChangelogSchemaName|Default schema name for the changelog tables. This defaults to None.|`liquibaseChangelogSchemaName := Some("my_schema")`|
|liquibaseDefaultCatalog|Default catalog name for the db if it isn't defined in the uri. This defaults to None.|`liquibaseDefaultCatalog := Some("my_catalog")`|
|liquibaseDefaultSchemaName|Default schema name for the db if it isn't defined in the uri. This defaults to None.|`liquibaseDefaultSchemaName := Some("my_schema")`|
|liquibaseChangelog|Full path to your changelog file. This defaults 'src/main/migrations/changelog.xml'.|`liquibaseChangelog := "other/path/dbchanges.xml"`|

## Tasks

|Task|Description|
|----|-----------|
|`liquibaseUpdate`|Run the liquibase migration|
|`liquibaseStatus`|Print count of yet to be run changesets|
|`liquibaseClearChecksums`|Removes all saved checksums from database log. Useful for 'MD5Sum Check Failed' errors|
|`liquibaseListLocks`|Lists who currently has locks on the database changelog|
|`liquibaseReleaseLocks`|Releases all locks on the database changelog.|
|`liquibaseValidateChangelog`|Checks changelog for errors.|
|`liquibaseDbDiff`|( this isn't implemented yet ) Generate changeSet(s) to make Test DB match Development|
|`liquibaseDbDoc`|Generates Javadoc-like documentation based on current database and change log|
|`liquibaseGenerateChangelog`|Writes Change Log XML to copy the current state of the database to the file defined in the changelog setting|
|`liquibaseChangelogSyncQql`|Writes SQL to mark all changes as executed in the database to STDOUT|
|`liquibaseTag {tag}`|Tags the current database state for future rollback with {tag}|
|`liquibaseRollback {tag}`|Rolls back the database to the the state is was when the {tag} was applied.|
|`liquibaseRollbackSql {tag}`|Writes SQL to roll back the database to that state it was in when the {tag} was applied to STDOUT|
|`liquibaseRollbackCount {int}`|Rolls back the last {int i} change sets applied to the database|
|`liquibaseRollbackCountSql {int}`|Writes SQL to roll back the last {int i} change sets to STDOUT applied to the database|
|`liquibaseRollbackToDate {yyyy-MM-dd HH:mm:ss}`|Rolls back the database to the the state it was at the given date/time. Date Format: yyyy-MM-dd HH:mm:ss|
|`liquibaseRollbackToDateSql { yyyy-MM-dd HH:mm:ss }`|Writes SQL to roll back the database to that state it was in at the given date/time version to STDOUT|
|`liquibaseFutureRollbackSql`|Writes SQL to roll back the database to the current state after the changes in the changelog have been applied.|
|`liquibaseDropAll`|Drop all tables|

Notes
------------------
This is a straight-up port of the previous plugin [sbt-liquibase-plugin](https://github.com/sbtliquibase/sbt-liquibase-plugin), with slight
updates to work with SBT 1.

Unfortunately this plugin doesn't have any real tests, however, is widely used in production by us and proved to work correctly.

Acknowledgements
---------------
Inspiration from previous work done by others on the following projects was an enourmous help.
 * [sbt-liquibase-plugin](https://github.com/sbtliquibase/sbt-liquibase-plugin) for the 0.13.x version
 * sbt-liquibase plugin for sbt 0.11/0.12 (thanks for actually making this plugin in the first place!)
 * sbt-web (helped to bring this up to speed with 0.13 plugin standards)
 * sbt-assembly (learned a lot about scripted sbt tests)



