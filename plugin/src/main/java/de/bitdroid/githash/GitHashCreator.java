package de.bitdroid.githash;


import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

public final class GitHashCreator {

	public void createJavaFile(
			String hash,
			String version,
			String packageName,
			String outDirName) throws IOException {

		MethodSpec constructor = MethodSpec
				.constructorBuilder()
				.addModifiers(Modifier.PRIVATE)
				.build();

		FieldSpec hashField = createField("COMMIT_HASH", hash);
		FieldSpec versionField = createField("VERSION", version);

		TypeSpec constantsClass = TypeSpec
				.classBuilder("GitConstants")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addField(hashField)
				.addField(versionField)
				.addMethod(constructor)
				.build();

		JavaFile javaFile = JavaFile
				.builder(packageName, constantsClass)
				.build();

		javaFile.writeTo(new File(outDirName));
	}


	private FieldSpec createField(String fieldName, String value) {
		return FieldSpec
				.builder(String.class, fieldName)
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
				.initializer("$S", value)
				.build();

	}

}
