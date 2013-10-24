package com.sorter.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.sorter.component.SortConfiguration;
import com.sorter.component.Sorter;
import com.sorter.reader.RawTextLineReader;
import com.sorter.util.ByteArrayComparator;
import com.sorter.writer.RawTextLineWriter;


public class TextFileSorter extends Sorter<byte[]>{

	public final static long MAX_HEAP_FOR_PRESORT = 500L * 1024 * 1024;

	public final static long MIN_HEAP_FOR_PRESORT = 10L * 1024 * 1024;

	public TextFileSorter() {
		this(new SortConfiguration());
	}

	public TextFileSorter(SortConfiguration config)
	{
		super(config,RawTextLineReader.factory(), RawTextLineWriter.factory(), new ByteArrayComparator());
	}

	//Main method for simple command-line operation for line-based
	public static void main(String[] args) throws Exception {
		if (args.length > 1) {
			System.err.println("Usage: java "+TextFileSorter.class.getName()+" [input-file]");
			System.err.println("(where input-file is optional; if missing, read from STDIN)");
			System.exit(1);
		}

		/*One more thing: use 50% of memory (but no more than 200 megs) for pre-sort
		minor tweak: consider first 40 megs to go for other overhead...*/
		long availMem = Runtime.getRuntime().maxMemory() - (40 * 1024 * 1024);
		long maxMem = (availMem >> 1);
		if (maxMem > MAX_HEAP_FOR_PRESORT) {
			maxMem = MAX_HEAP_FOR_PRESORT;
		} else if (maxMem < MIN_HEAP_FOR_PRESORT) {
			maxMem = MIN_HEAP_FOR_PRESORT;
		}
		final TextFileSorter sorter = new TextFileSorter(new SortConfiguration().withMaxMemoryUsage(maxMem));
		final InputStream in;

		if (args.length == 0) {
			in = System.in;
		} else {
			File input = new File(args[0]);
			if (!input.exists() || input.isDirectory()) {
				System.err.println("File '"+input.getAbsolutePath()+"' does not exist (or is not file)");
				System.exit(2);
			}
			in = new FileInputStream(input);
		}
		// To be able to print out progress, need to spin one additional thread...
		new Thread(new Runnable() {
			@Override
			public void run() {
				final long start = System.currentTimeMillis();
				try {
					while (!sorter.isCompleted()) {
						Thread.sleep(5000L);
						if (sorter.isPreSorting()) {
							System.err.printf(" pre-sorting: %d files written\n", sorter.getNumberOfPreSortFiles());
						} else if (sorter.isSorting()) {
							System.err.printf(" sorting, round: %d/%d\n", sorter.getSortRound(), sorter.getNumberOfSortRounds());
						}
					}
					double secs = (System.currentTimeMillis() - start) / 1000.0;
					System.err.printf("Completed: took %.1f seconds.\n", secs);
				} catch (InterruptedException e) {
					double secs = (System.currentTimeMillis() - start) / 1000.0;
					System.err.printf("[INTERRUPTED] -- took %.1f seconds.\n", secs);
				}
			}
		}).start();
		sorter.sort(in, System.out);
	}
}
