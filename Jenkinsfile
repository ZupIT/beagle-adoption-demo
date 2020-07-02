@Library('ZupSharedLibs@darwin') _

node {
    try {

        buildDockerBuilder {
            dockerRepositoryName = "products-hack-day"
            dockerBuilderImage = "adoptopenjdk/openjdk11:jdk-11.0.4_11-alpine-slim"
            dockerFileBuilder = "DockerfileBuilder"
            dockerFileLocation = "challenge/backend/."
            team = "Realwave"
        }

        mvnBuildWithCompose {
            composeFileName = "docker-compose-ci.yml"
            composeFileLocation = "challenge/backend/."
            composeService = "backend"
            composeProjectName = "backend"
            team = "Realwave"
            useBuilder="true"
        }

        buildDockerContainer {
            dockerRepositoryName = "backend"
            dockerFileLocation = "challenge/backend/."
            team = "Realwave"
        }

    } catch (e) {
        notifyBuildStatus {
            buildStatus = "FAILED"
        }
        throw e
    }

}
