package com.sorter.util;


import java.io.File;
import java.io.IOException;

public class StdTempFileProvider {
	/**
	 * Default temporary file prefix to use.
	 */
	public final static String DEFAULT_PREFIX = "j-merge-sort-";

	/**
	 * Default temporary file suffix to use.
	 */
	public final static String DEFAULT_SUFFIX = ".tmp";

	protected final String _prefix;
	protected final String _suffix;

	public StdTempFileProvider() { this(DEFAULT_PREFIX, DEFAULT_SUFFIX); }
	public StdTempFileProvider(String prefix, String suffix) {
		_prefix = prefix;
		_suffix = suffix;
	}

	public File provide() throws IOException
	{
		File f = File.createTempFile(_prefix, _suffix);
		f.deleteOnExit();
		return f;
	}
}
