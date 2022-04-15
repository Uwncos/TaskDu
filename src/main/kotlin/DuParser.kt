import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option

class DuParser {
    @Option(name = "-h", usage = "Readable form")
    private var humanForm = false

    @Option(name = "-c", usage = "Total size")
    private var totalSize = false

    @Option(name = "--si", usage = "International system")
    private var base = false

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private var filesNames: ArrayList<String> = ArrayList()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DuParser().launch(args)
        }
    }

     private fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            System.err.println(e.message)
            System.err.println("java -jar TaskDu-1.0-SNAPSHOT.jar [-c -h --si] file(-s)")
            parser.printUsage(System.err)
            throw IllegalArgumentException("")
        }
        //        DuKt.du(humanForm, totalSize, base, filesNames);
        val Du = Du(humanForm, totalSize, base)
        try {
            Du.du(filesNames)
        } catch (e: Exception) {
            System.err.println(e.message)
        }
    }
}