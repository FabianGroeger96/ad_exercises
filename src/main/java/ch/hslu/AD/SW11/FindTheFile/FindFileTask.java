package ch.hslu.AD.SW11.FindTheFile;

import java.io.File;
import java.io.FileFilter;
import java.util.Optional;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Finde das File
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class FindFileTask extends CountedCompleter<Optional<File>> {

    private static final FileFilter filefilter = pathname -> pathname.isDirectory() || pathname.isFile();

    private final File dir;
    private final String regex;
    private final AtomicReference<File> result;

    public FindFileTask(final File dir, final String regex) {
        this(null, dir, regex, new AtomicReference<File>(null));
    }

    public FindFileTask(final CountedCompleter<?> parent, final File dir, final String regex, final AtomicReference<File> result) {
        super(parent);
        this.dir = dir;
        this.regex = regex;
        this.result = result;
    }

    @Override
    public void compute() {
        File[] entries = dir.listFiles(filefilter);

        if (entries != null) {
            for (File entry : entries) {
                if (result.get() != null) {
                    break;
                }

                if (entry.isDirectory()) {
                    addToPendingCount(1);
                    FindFileTask task = new FindFileTask(this, entry, this.regex, result);
                    task.fork();
                } else {
                    String tmp = entry.getPath();
                    if (tmp.matches(regex) && result.compareAndSet(null, entry)) {
                        quietlyCompleteRoot();
                        break;
                    }
                }
            }
        }

        tryComplete();
    }

    @Override
    public Optional<File> getRawResult() {
        File res = result.get();
        if (res == null) {
            return Optional.empty();
        } else {
            return Optional.of(res);
        }
    }
}
