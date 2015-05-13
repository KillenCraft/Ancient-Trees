package com.scottkillen.mod.koresample.common.util;

import com.google.common.base.Objects;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

@SuppressWarnings({ "WeakerAccess", "StandardVariableNames" })
public class WeightedSet<T>  implements Collection<T>
{
    private final Random random;
    private final Multiset<T> elements = HashMultiset.create();

    public static <T> WeightedSet<T> newWeightedSet() { return new WeightedSet<T>(); }

    public static <T> WeightedSet<T> newWeightedSet(Random random) { return new WeightedSet<T>(random); }

    public WeightedSet()
    {
        this(new Random());
    }

    public WeightedSet(Random random)
    {
        checkNotNull(random, "Random generator is required.");

        this.random = random;
    }

    public T randomPick()
    {
        final int pick = random.nextInt(elements.size());
        int count = 0;
        for (final T t : elements.elementSet())
        {
            count += elements.count(t);
            if (count >= pick) return t;
        }
        //noinspection ReturnOfNull
        return null;
    }

    public void setWeight(T t, int weight) { elements.setCount(t, weight); }

    @Override
    public int size() { return elements.size(); }

    @Override
    public boolean isEmpty() { return elements.isEmpty(); }

    @Override
    public boolean contains(Object o) { return elements.contains(o); }

    @Override
    public Iterator<T> iterator() { return elements.iterator(); }

    @Override
    public Object[] toArray() { return elements.toArray(); }

    @SuppressWarnings({ "SuspiciousToArrayCall", "TypeParameterHidesVisibleType" })
    @Override
    public <T> T[] toArray(T[] a) { return elements.toArray(a); }

    @Override
    public boolean add(T t) { return elements.add(t); }

    @Override
    public boolean remove(Object o) { return elements.remove(o); }

    @Override
    public boolean containsAll(Collection<?> c) { return elements.containsAll(c); }

    @Override
    public boolean addAll(Collection<? extends T> c) { return elements.addAll(c); }

    @Override
    public boolean removeAll(Collection<?> c) { return elements.removeAll(c); }

    @Override
    public boolean retainAll(Collection<?> c) { return elements.retainAll(c); }

    @Override
    public void clear() { elements.clear(); }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof WeightedSet)) return false;

        final WeightedSet<?> that = (WeightedSet<?>) o;
        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() { return elements.hashCode(); }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("random", random).add("elements", elements).toString();
    }
}
