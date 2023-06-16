package com.charliecwb.springbootmongodb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.charliecwb.springbootmongodb.resources.util.Util;
import com.charliecwb.springbootmongodb.services.NexmoService;
import com.nexmo.client.verify.VerifyClient;

@SpringBootApplication
public class SpringbootMongodbApplication {
	public static void main(String[] args) {
		if (!verifyIfExistKeysOnSO()) {
			generateKey();
		}	
		
		SpringApplication.run(SpringbootMongodbApplication.class, args);
	}

	public static void generateKey() {
		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Util.ALGORITHM);
			keyGen.initialize(1024);
			final KeyPair key = keyGen.generateKeyPair();

			File keyPrivate = new File(Util.PATH_PRIVATE_KEY);
			File keyPublic = new File(Util.PATH_PUBLIC_KEY);

			if (keyPrivate.getParentFile() != null) {
				keyPrivate.getParentFile().mkdirs();
			}

			keyPrivate.createNewFile();

			if (keyPublic.getParentFile() != null) {
				keyPublic.getParentFile().mkdirs();
			}

			keyPublic.createNewFile();

			ObjectOutputStream keyPublicOS = new ObjectOutputStream(new FileOutputStream(keyPublic));
			keyPublicOS.writeObject(key.getPublic());
			keyPublicOS.close();

			ObjectOutputStream keyPrivateOS = new ObjectOutputStream(new FileOutputStream(keyPrivate));
			keyPrivateOS.writeObject(key.getPrivate());
			keyPrivateOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean verifyIfExistKeysOnSO() {
		File keyPrivate = new File(Util.PATH_PRIVATE_KEY);
		File keyPublic = new File(Util.PATH_PUBLIC_KEY);

		if (keyPrivate.exists() && keyPublic.exists()) {
			return true;
		}

		return false;
	}

}
