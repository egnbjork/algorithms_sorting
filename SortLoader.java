import java.util.*;
import java.net.*;
import java.io.*;
import sortingalgorithms.*;

public class SortLoader {

    public static List<ArraySort> getArraySorts() {
        List<ArraySort> arraySorts = new ArrayList();

        List<Class> foundClasses = getClassOfPackage("sortingalgorithms");

        try {
            for(Class clazz : foundClasses) {
                arraySorts.add((ArraySort) clazz.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arraySorts;
    }


    private static List<Class> getClassOfPackage(String packageName) {

        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();

        assert classLoader != null;
        String path = packageName.replace('.', '/');

        ArrayList<Class> classes = new ArrayList<>();

        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }

            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch(Exception e) {
            System.out.println("Cannot load sort algoritm classes");
            e.printStackTrace();
        }

        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) throws Exception {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file,
                        packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".java") && 
                    !file.getName().equals("ArraySort.java")) {

                classes.add(Class.forName("sortingalgorithms." + file.getName().substring(0, file.getName().length() - 5)));
            }
        }
        return classes;
    }
}

