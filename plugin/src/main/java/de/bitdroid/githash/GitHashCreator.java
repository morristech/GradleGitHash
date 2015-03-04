package de.bitdroid.githash;


import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

public final class GitHashCreator {

	public void createJavaFile(String hash, String packageName, String outDirName) throws IOException {
		MethodSpec constructor = MethodSpec
				.constructorBuilder()
				.addModifiers(Modifier.PRIVATE)
				.build();

		FieldSpec hashField = FieldSpec
				.builder(String.class, "COMMIT_HASH")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
				.initializer("$S", hash)
				.build();

		TypeSpec constantsClass = TypeSpec
				.classBuilder("GitConstants")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addField(hashField)
				.addMethod(constructor)
				.build();

		JavaFile javaFile = JavaFile
				.builder(packageName, constantsClass)
				.build();

		javaFile.writeTo(new File(outDirName));
	}

}
