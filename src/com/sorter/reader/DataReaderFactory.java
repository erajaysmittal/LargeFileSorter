package com.sorter.reader;
import java.io.IOException;
import java.io.InputStream;

public abstract class DataReaderFactory<T> {
	public abstract DataReader<T> constructReader(InputStream in) throws IOException;
}