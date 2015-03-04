package de.bitdroid.githash

import org.gradle.api.DefaultTask
import org.gradle.api.GradleScriptException
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

public class GitHashTask extends DefaultTask {

    @OutputDirectory File outputDir

    @TaskAction
    public void execute(IncrementalTaskInputs inputs) {
        String commitHash
        def gitFolder = "${project.rootProject.projectDir}/.git/"
        def hashLength = 12
        def headEntries = new File(gitFolder + 'HEAD').text.split(':')
        def isCommit = headEntries.length == 1
        if(isCommit) {
            commitHash = headEntries[0].trim().take(hashLength)
        }  else {
            def refHead = new File(gitFolder + headEntries[1].trim())
            commitHash = refHead.text.trim().take(hashLength)
        }

        try {
            new GitHashCreator().createJavaFile(
                    commitHash,
                    project.gitHashSettings.packageName.toString(),
                    outputDir.getPath())
        } catch (IOException e) {
            throw new GradleScriptException("failed to write classes to file", e)
        }
    }

}
