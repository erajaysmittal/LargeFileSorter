package com.sorter.component;

import com.sorter.util.StdTempFileProvider;


/**
 * Configuration object used for changing details of sorting
 * process. Default settings are usable, so often
 * instance is created without arguments and used as is.
 */
public class SortConfiguration {
	/**
	 * By default we will use 40 megs for pre-sorting.
	 */
	public final static long DEFAULT_MEMORY_USAGE = 40 * 1024 * 1024;

	/**
	 * Default merge sort is 16-way sort (using 16 input files concurrently)
	 */
	public final static int DEFAULT_MERGE_FACTOR = 16;

	protected int _mergeFactor;

	protected long _maxMemoryUsage;

	protected StdTempFileProvider _tempFileProvider;

	/*
    /************************************************************************
    /* Construction
    /************************************************************************
	 */

	public SortConfiguration()
	{
		_mergeFactor = DEFAULT_MERGE_FACTOR;
		_maxMemoryUsage = DEFAULT_MEMORY_USAGE;
		_tempFileProvider = new StdTempFileProvider();
	}

	protected SortConfiguration(SortConfiguration base, int mergeFactor) {
		_maxMemoryUsage = base._maxMemoryUsage;
		_mergeFactor = mergeFactor;
		_tempFileProvider = base._tempFileProvider;
	}

	protected SortConfiguration(SortConfiguration base, long maxMem) {
		_maxMemoryUsage = maxMem;
		_mergeFactor = base._mergeFactor;
		_tempFileProvider = base._tempFileProvider;
	}

	protected SortConfiguration(SortConfiguration base, StdTempFileProvider prov) {
		_mergeFactor = base._mergeFactor;
		_maxMemoryUsage = base._maxMemoryUsage;
		_tempFileProvider = prov;
	}

	/*
    /************************************************************************
    /* Accessors
    /************************************************************************
	 */

	public int getMergeFactor() { return _mergeFactor; }

	public long getMaxMemoryUsage() { return _maxMemoryUsage; }

	public StdTempFileProvider getTempFileProvider() { return _tempFileProvider; }

	/*
    /************************************************************************
    /* Fluent construction methods
    /************************************************************************
	 */

	/**
	 * Method for constructing configuration instance that defines that maximum amount
	 * of memory to use for pre-sorting. This is generally a crude approximation and
	 * implementations make best effort to honor it.
	 * 
	 * @param maxMem Maximum memory that pre-sorted should use for in-memory sorting
	 * @return New
	 */
	public SortConfiguration withMaxMemoryUsage(long maxMem)
	{
		if (maxMem == _maxMemoryUsage) {
			return this;
		}
		return new SortConfiguration(this, maxMem);
	}

	public SortConfiguration withTempFileProvider(StdTempFileProvider provider)
	{
		if (provider == _tempFileProvider) {
			return this;
		}
		return new SortConfiguration(this, provider);
	}

}