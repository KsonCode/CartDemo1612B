package com.bwie.cartdemo1612b.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bwie.cartdemo1612b.entity.ProductDetailBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PRODUCT_DETAIL_BEAN".
*/
public class ProductDetailBeanDao extends AbstractDao<ProductDetailBean, Long> {

    public static final String TABLENAME = "PRODUCT_DETAIL_BEAN";

    /**
     * Properties of entity ProductDetailBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CommodityId = new Property(1, String.class, "commodityId", false, "COMMODITY_ID");
        public final static Property Count = new Property(2, int.class, "count", false, "COUNT");
    }


    public ProductDetailBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ProductDetailBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PRODUCT_DETAIL_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"COMMODITY_ID\" TEXT," + // 1: commodityId
                "\"COUNT\" INTEGER NOT NULL );"); // 2: count
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PRODUCT_DETAIL_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProductDetailBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String commodityId = entity.getCommodityId();
        if (commodityId != null) {
            stmt.bindString(2, commodityId);
        }
        stmt.bindLong(3, entity.getCount());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProductDetailBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String commodityId = entity.getCommodityId();
        if (commodityId != null) {
            stmt.bindString(2, commodityId);
        }
        stmt.bindLong(3, entity.getCount());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ProductDetailBean readEntity(Cursor cursor, int offset) {
        ProductDetailBean entity = new ProductDetailBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // commodityId
            cursor.getInt(offset + 2) // count
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProductDetailBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCommodityId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCount(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ProductDetailBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ProductDetailBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ProductDetailBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}