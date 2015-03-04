package de.bitdroid.githash
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

public final class GitHashPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("gitHashSettings", GitHashExtension)

        def task = project.tasks.create('gitHash', GitHashTask)
        task.outputDir = new File("${project.projectDir}/target/generated-sources/githash")

        boolean hasJava = project.plugins.hasPlugin JavaPlugin
        if (hasJava) {
            project.compileJava.dependsOn task
            project.compileJava.source += task.outputs.files
        }
    }

}