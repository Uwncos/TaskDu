

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;

import java.util.List;

public class DuParser {

    @Option(name = "-h", usage = "Readable form")
    private Boolean humanForm = false;

    @Option(name = "-c", usage = "Total size")
    private Boolean totalSize = false;

    @Option(name = "--si", usage = "International system")
    private Boolean base = false;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private List<String> filesNames;


    public static void main(String[] args) {
        new DuParser().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar TaskDu-1.0-SNAPSHOT.jar [-c -h --si] file(-s)");
            parser.printUsage(System.err);
            throw new IllegalArgumentException("");
        }
        DuKt.du(humanForm, totalSize, base, filesNames);
    }
}
