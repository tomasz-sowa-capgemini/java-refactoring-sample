/*
 * Copyright 2007-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;

/**
 * Maven Wrapper Downloader
 * <p>
 * Downloads the Maven Wrapper JAR file if not already present.
 */
public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "0.5.6";

    /** Default URL to download the maven-wrapper.jar from if no custom URL is provided. */
    private static final String DEFAULT_DOWNLOAD_URL =
            "https://repo.maven.apache.org/maven2/io/takari/maven-wrapper/"
                    + WRAPPER_VERSION + "/maven-wrapper-" + WRAPPER_VERSION + ".jar";

    /** Path to the maven-wrapper.properties file. */
    private static final String MAVEN_WRAPPER_PROPERTIES_PATH = ".mvn/wrapper/maven-wrapper.properties";

    /** Path where the maven-wrapper.jar will be saved. */
    private static final String MAVEN_WRAPPER_JAR_PATH = ".mvn/wrapper/maven-wrapper.jar";

    /** Property name used to override the default download URL. */
    private static final String PROPERTY_NAME_WRAPPER_URL = "wrapperUrl";

    /**
     * Entry point for downloading the Maven Wrapper JAR.
     *
     * @param args command line arguments; expects the base directory as the first argument
     */
    public static void main(String[] args) {
        System.out.println("- Downloader started");

        if (args.length == 0) {
            System.out.println("- ERROR: Base directory argument missing.");
            System.exit(1);
        }

        File baseDirectory = new File(args[0]);
        System.out.println("- Using base directory: " + baseDirectory.getAbsolutePath());

        String downloadUrl = resolveDownloadUrl(baseDirectory);
        System.out.println("- Downloading from: " + downloadUrl);

        File outputFile = new File(baseDirectory, MAVEN_WRAPPER_JAR_PATH);
        ensureOutputDirectoryExists(outputFile);

        System.out.println("- Downloading to: " + outputFile.getAbsolutePath());

        try {
            downloadFileFromURL(downloadUrl, outputFile);
            System.out.println("Done");
            System.exit(0);
        } catch (Throwable e) {
            System.out.println("- Error downloading");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Resolves the download URL from properties file or defaults to the predefined URL.
     *
     * @param baseDirectory the base directory containing the properties file
     * @return the resolved download URL
     */
    private static String resolveDownloadUrl(File baseDirectory) {
        File propertiesFile = new File(baseDirectory, MAVEN_WRAPPER_PROPERTIES_PATH);
        String url = DEFAULT_DOWNLOAD_URL;

        if (propertiesFile.exists()) {
            try (FileInputStream inputStream = new FileInputStream(propertiesFile)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                url = properties.getProperty(PROPERTY_NAME_WRAPPER_URL, url);
            } catch (IOException e) {
                System.out.println("- ERROR loading '" + MAVEN_WRAPPER_PROPERTIES_PATH + "'");
            }
        }
        return url;
    }

    /**
     * Ensures that the output directory exists before downloading.
     *
     * @param outputFile the file to be downloaded
     */
    private static void ensureOutputDirectoryExists(File outputFile) {
        File parentDirectory = outputFile.getParentFile();
        if (!parentDirectory.exists() && !parentDirectory.mkdirs()) {
            System.out.println("- ERROR creating output directory '" + parentDirectory.getAbsolutePath() + "'");
        }
    }

    /**
     * Downloads a file from the specified URL to the given destination.
     *
     * @param urlString   the URL to download from
     * @param destination the destination file
     * @throws Exception if an error occurs during download
     */
    private static void downloadFileFromURL(String urlString, File destination) throws Exception {
        configureAuthentication();

        URL website = new URL(urlString);
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(website.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(destination)) {
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }
    }

    /**
     * Configures authentication if environment variables are set.
     */
    private static void configureAuthentication() {
        String username = System.getenv("MVNW_USERNAME");
        String password = System.getenv("MVNW_PASSWORD");

        if (username != null && password != null) {
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password.toCharArray());
                }
            });
        }
    }
}