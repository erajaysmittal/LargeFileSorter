package com.sorter.writer;
import java.io.IOException;
import java.io.OutputStream;

public abstract class DataWriterFactory<T> {
	public abstract DataWriter<T> constructWriter(OutputStream out) throws IOException;
}