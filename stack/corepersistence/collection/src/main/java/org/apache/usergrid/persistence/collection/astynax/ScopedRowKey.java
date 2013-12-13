package org.apache.usergrid.persistence.collection.astynax;


import org.apache.usergrid.persistence.collection.OrganizationScope;

import com.google.common.base.Preconditions;


/**
 * A row key that is within a Scope.  Every I/O operation should be using this class.  No keys should be allowed that
 * aren't within a Scope
 *
 * @author tnine
 */
public class ScopedRowKey<S extends OrganizationScope, K> {

    private final S collectionScope;

    private final K key;


    public ScopedRowKey( final S collectionScope, final K key ) {
        Preconditions.checkNotNull( collectionScope, "CollectionScope is required" );
        Preconditions.checkNotNull( key, "Key is required" );

        this.collectionScope = collectionScope;
        this.key = key;
    }


    /**
     * Get the stored collectionScope
     */
    public S getScope() {
        return collectionScope;
    }


    /**
     * Get the suffix key
     */
    public K getKey() {
        return key;
    }


    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !( o instanceof ScopedRowKey ) ) {
            return false;
        }

        final ScopedRowKey that = ( ScopedRowKey ) o;

        if ( !key.equals( that.key ) ) {
            return false;
        }
        if ( !collectionScope.equals( that.collectionScope ) ) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {
        int result = collectionScope.hashCode();
        result = 31 * result + key.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "ScopedRowKey{" +
                "collectionScope=" + collectionScope +
                ", key=" + key +
                '}';
    }


    /**
     * Utility function to generate a new key from the collectionScope
     */
    public static <S extends OrganizationScope, K> ScopedRowKey<S, K> fromKey( final S scope, K key ) {
        return new ScopedRowKey<S, K>( scope, key );
    }
}