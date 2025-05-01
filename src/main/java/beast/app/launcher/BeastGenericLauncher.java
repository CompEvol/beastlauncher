package beast.app.launcher;

import beast.pkgmgmt.launcher.BeastLauncher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Generic launcher for BEAST2-based applications that extends the BeastLauncher
 * to run models with proper package initialization.
 *
 * Usage: BeastGenericLauncher --main [mainClass] [other args]
 * If --main is not specified, defaults to beastfx.app.beast.BeastMain
 * 
 * Note: This launcher is deliberately Java 1.6 compatible.
 */
public class BeastGenericLauncher extends BeastLauncher {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        
        // Extract main class argument if provided
        String mainClass = "beastfx.app.beast.BeastMain"; // Default main class
        String[] remainingArgs = args;
        
        // Look for --main argument (Java 1.6 compatible code)
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("--main")) {
                mainClass = args[i + 1];
                
                // Remove --main and its value from args
                String[] newArgs = new String[args.length - 2];
                System.arraycopy(args, 0, newArgs, 0, i);
                if (i + 2 < args.length) {
                    System.arraycopy(args, i + 2, newArgs, i, args.length - i - 2);
                }
                remainingArgs = newArgs;
                break;
            }
        }
        
        // First, check Java version as the parent does
        if (javaVersionCheck("BEAST Generic Launcher")) {
            testCudaStatusOnMac();
            
            boolean useStrictVersions = false;
            for (String arg : remainingArgs) {
                if (arg.equals("-strictversions")) {
                    useStrictVersions = true;
                }
            }
            
            // Get the classpath the same way as BeastLauncher
            String classpath = getPath(useStrictVersions, remainingArgs.length > 0 ? remainingArgs[remainingArgs.length - 1] : null);
            
            // Set BEAST_PACKAGE_PATH system property if not already set
            if (System.getProperty("BEAST_PACKAGE_PATH") == null) {
                String packagePath = getPackageUserDir();
                System.out.println("Setting BEAST_PACKAGE_PATH to: " + packagePath);
                System.setProperty("BEAST_PACKAGE_PATH", packagePath);
            }
            
            // Print the main class we're running
            System.out.println("Running main class: " + mainClass);
            
            // Run the specified main class
            run(classpath, mainClass, remainingArgs);
        }
    }
}