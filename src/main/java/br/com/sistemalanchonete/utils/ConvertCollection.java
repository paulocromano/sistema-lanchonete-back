package br.com.sistemalanchonete.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class ConvertCollection {

	public static <E, R> Collection<R> convertToCollection(
			Collection<E> collection, Function<E, R> function, 
			Supplier<Collection<R>> supplier) {

		return collection.stream().map(function::apply)
				.collect(Collectors.toCollection(supplier));
	}
	
	public static <E, R> List<R> convertToList(Collection<E> collection, 
			Function<E, R> function) {
		
		return (List<R>) convertToCollection(collection, 
				function, ArrayList::new);
	}
	
	public static <E, R> Set<R> convertToSet(Collection<E> collection, 
			Function<E, R> function) {
		
		return (Set<R>) convertToCollection(collection, 
				function, HashSet::new);
	}
	
	
	public static <E, R> Collection<R> convertToOrdenedCollection(
			Collection<E> collection, Function<E, R> function, 
			Comparator<E> comparator, Supplier<Collection<R>> supplier) {

		return collection.stream().sorted(comparator).map(function::apply)
				.collect(Collectors.toCollection(supplier));
	}
	
	public static <E, R> List<R> convertToOrdenedList(
			Collection<E> collection, Function<E, R> function,
			Comparator<E> comparator) {
		
		return (List<R>) convertToOrdenedCollection(collection, 
				function, comparator, ArrayList::new);
	}
}
