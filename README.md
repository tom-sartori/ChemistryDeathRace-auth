# chemistrydeathrace-auth

## Description

ChemistryDeathRace is a school project made at [Polytech](https://www.polytech.umontpellier.fr/), Montpellier, France, by [Romain Frezier](https://github.com/romainfrezier) and [Tom Sartori](https://github.com/tom-sartori) for [ENSCM](https://www.enscm.fr/en/).

This project is the authentication API of the ChemistryDeathRace project. It uses [Quarkus](https://quarkus.io), the Supersonic Subatomic Java Framework.

<a target="_blank" href="https://github.com/tom-sartori/ChemistryDeathRace">
  <img alt="github link" src="https://img.shields.io/badge/Chemistry Death Race-GLOBAL-red?style=for-the-badge&logo=github">
</a>
<br>
<a target="_blank" href="https://github.com/tom-sartori/ChemistryDeathRace-ui">
  <img alt="github link" src="https://img.shields.io/badge/Chemistry Death Race-UI-green?style=for-the-badge&logo=github">
</a>
<br>
<a target="_blank" href="https://github.com/tom-sartori/ChemistryDeathRace-adm">
  <img alt="github link" src="https://img.shields.io/badge/Chemistry Death Race-ADM-green?style=for-the-badge&logo=github">
</a>
<br>
<a target="_blank" href="https://github.com/tom-sartori/ChemistryDeathRace-api">
  <img alt="github link" src="https://img.shields.io/badge/Chemistry Death Race-API-green?style=for-the-badge&logo=github">
</a> 
<br>
<a target="_blank" href="https://github.com/tom-sartori/ChemistryDeathRace-auth">
  <img alt="github link" src="https://img.shields.io/badge/Chemistry Death Race-AUTH-green?style=for-the-badge&logo=github">
</a>
<br>
<a target="_blank" href="https://github.com/tom-sartori/ChemistryDeathRace-doc">
  <img alt="github link" src="https://img.shields.io/badge/Chemistry Death Race-DOC-green?style=for-the-badge&logo=github">
</a>

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/chemistrydeathrace-auth-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
