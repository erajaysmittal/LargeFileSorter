package com.sorter.component;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.Iterator;

import com.sorter.iterator.IteratingSorter;
import com.sorter.reader.DataReader;
import com.sorter.reader.DataReaderFactory;
import com.sorter.writer.DataWriter;
import com.sorter.writer.DataWriterFactory;


/**
 * Main entry point for sorting functionality; object that drives
 * the sorting process from pre-sort to final output.
 */
public class Sorter<T> extends IteratingSorter<T> {
	public Sorter(SortConfiguration config, DataReaderFactory<T> readerFactory, DataWriterFactory<T> writerFactory, Comparator<T> comparator) {
		super(config, readerFactory, writerFactory, comparator);
	}

	public Sorter() {
		super();
	}

	public Sorter(SortConfiguration config) {
		super(config);
	}

	protected Sorter<T> withReaderFactory(DataReaderFactory<T> f) {
		return new Sorter<T>(_config, f, _writerFactory, _comparator);
	}

	protected Sorter<T> withWriterFactory(DataWriterFactory<T> f) {
		return new Sorter<T>(_config, _readerFactory, f, _comparator);
	}

	protected Sorter<T> withComparator(Comparator<T> cmp) {
		return new Sorter<T>(_config, _readerFactory, _writerFactory, cmp);
	}

	/**
	 * Method that will perform full sort on specified input, writing results
	 * into specified destination. Data conversions needed are done
	 */
	public void sort(InputStream source, OutputStream destination)throws IOException {
		sort(_readerFactory.constructReader(source), _writerFactory.constructWriter(destination));
	}

	/**
	 * Method that will perform full sort on input data read using given
	 * DataReader, and written out using specified DataWriter.
	 * Conversions to and from intermediate sort files is done using DataReaderFactory and DataWriterFactory configured for this sorter.
	 */
	public boolean sort(DataReader<T> inputReader, DataWriter<T> resultWriter) throws IOException {
		Iterator<T> it = super.sort(inputReader);
		if(it == null) {
			return false;
		}
		try {
			while(it.hasNext()) {
				T value = it.next();
				resultWriter.writeEntry(value);
			}
			resultWriter.close();
		} finally {
			super.close();
		}
		return true;
	}
}