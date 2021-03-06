package de.gesellix.gradle.docker.tasks

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class DockerKillTask extends DockerTask {

    @Input
    def containerId

    def result

    DockerKillTask() {
        description = "Kill a running container"
        group = "Docker"
    }

    @TaskAction
    def kill() {
        logger.info "docker kill"
        result = getDockerClient().kill(getContainerId())
        return result
    }
}
