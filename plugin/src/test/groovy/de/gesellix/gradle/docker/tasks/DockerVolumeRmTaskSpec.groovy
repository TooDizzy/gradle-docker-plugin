package de.gesellix.gradle.docker.tasks

import de.gesellix.docker.client.DockerClient
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class DockerVolumeRmTaskSpec extends Specification {

    def project
    def task
    def dockerClient = Mock(DockerClient)

    def setup() {
        project = ProjectBuilder.builder().build()
        task = project.task('dockerRmVolume', type: DockerVolumeRmTask)
        task.dockerClient = dockerClient
    }

    def "delegates to dockerClient and saves result"() {
        given:
        task.configure {
            volumeName = "foo"
        }

        when:
        task.execute()

        then:
        1 * dockerClient.rmVolume("foo") >> [status: [code: 204]]

        and:
        task.response == [status: [code: 204]]
    }
}
