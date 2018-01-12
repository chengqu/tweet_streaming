/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package tech.chengqu.avro;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
/** A basic schema for storing Twitter messages */
@org.apache.avro.specific.AvroGenerated
public class Tweet extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4742627905634449395L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Tweet\",\"namespace\":\"tech.chengqu.avro\",\"doc\":\"A basic schema for storing Twitter messages\",\"fields\":[{\"name\":\"username\",\"type\":\"string\",\"doc\":\"Name of the user account on Twitter.com\"},{\"name\":\"text\",\"type\":\"string\",\"doc\":\"The content of the user's Twitter message\"},{\"name\":\"timestamp\",\"type\":\"long\",\"doc\":\"Unix epoch time in seconds\"},{\"name\":\"entities\",\"type\":{\"type\":\"record\",\"name\":\"Entity\",\"fields\":[{\"name\":\"hashtags\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Hashtag\",\"fields\":[{\"name\":\"indices\",\"type\":\"int\"},{\"name\":\"text\",\"type\":\"string\"}]}}}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Tweet> ENCODER =
      new BinaryMessageEncoder<Tweet>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Tweet> DECODER =
      new BinaryMessageDecoder<Tweet>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Tweet> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Tweet> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Tweet>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Tweet to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Tweet from a ByteBuffer. */
  public static Tweet fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Name of the user account on Twitter.com */
  @Deprecated public java.lang.CharSequence username;
  /** The content of the user's Twitter message */
  @Deprecated public java.lang.CharSequence text;
  /** Unix epoch time in seconds */
  @Deprecated public long timestamp;
  @Deprecated public tech.chengqu.avro.Entity entities;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Tweet() {}

  /**
   * All-args constructor.
   * @param username Name of the user account on Twitter.com
   * @param text The content of the user's Twitter message
   * @param timestamp Unix epoch time in seconds
   * @param entities The new value for entities
   */
  public Tweet(java.lang.CharSequence username, java.lang.CharSequence text, java.lang.Long timestamp, tech.chengqu.avro.Entity entities) {
    this.username = username;
    this.text = text;
    this.timestamp = timestamp;
    this.entities = entities;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return username;
    case 1: return text;
    case 2: return timestamp;
    case 3: return entities;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: username = (java.lang.CharSequence)value$; break;
    case 1: text = (java.lang.CharSequence)value$; break;
    case 2: timestamp = (java.lang.Long)value$; break;
    case 3: entities = (tech.chengqu.avro.Entity)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'username' field.
   * @return Name of the user account on Twitter.com
   */
  public java.lang.CharSequence getUsername() {
    return username;
  }

  /**
   * Sets the value of the 'username' field.
   * Name of the user account on Twitter.com
   * @param value the value to set.
   */
  public void setUsername(java.lang.CharSequence value) {
    this.username = value;
  }

  /**
   * Gets the value of the 'text' field.
   * @return The content of the user's Twitter message
   */
  public java.lang.CharSequence getText() {
    return text;
  }

  /**
   * Sets the value of the 'text' field.
   * The content of the user's Twitter message
   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return Unix epoch time in seconds
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * Unix epoch time in seconds
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'entities' field.
   * @return The value of the 'entities' field.
   */
  public tech.chengqu.avro.Entity getEntities() {
    return entities;
  }

  /**
   * Sets the value of the 'entities' field.
   * @param value the value to set.
   */
  public void setEntities(tech.chengqu.avro.Entity value) {
    this.entities = value;
  }

  /**
   * Creates a new Tweet RecordBuilder.
   * @return A new Tweet RecordBuilder
   */
  public static tech.chengqu.avro.Tweet.Builder newBuilder() {
    return new tech.chengqu.avro.Tweet.Builder();
  }

  /**
   * Creates a new Tweet RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Tweet RecordBuilder
   */
  public static tech.chengqu.avro.Tweet.Builder newBuilder(tech.chengqu.avro.Tweet.Builder other) {
    return new tech.chengqu.avro.Tweet.Builder(other);
  }

  /**
   * Creates a new Tweet RecordBuilder by copying an existing Tweet instance.
   * @param other The existing instance to copy.
   * @return A new Tweet RecordBuilder
   */
  public static tech.chengqu.avro.Tweet.Builder newBuilder(tech.chengqu.avro.Tweet other) {
    return new tech.chengqu.avro.Tweet.Builder(other);
  }

  /**
   * RecordBuilder for Tweet instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Tweet>
    implements org.apache.avro.data.RecordBuilder<Tweet> {

    /** Name of the user account on Twitter.com */
    private java.lang.CharSequence username;
    /** The content of the user's Twitter message */
    private java.lang.CharSequence text;
    /** Unix epoch time in seconds */
    private long timestamp;
    private tech.chengqu.avro.Entity entities;
    private tech.chengqu.avro.Entity.Builder entitiesBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(tech.chengqu.avro.Tweet.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.entities)) {
        this.entities = data().deepCopy(fields()[3].schema(), other.entities);
        fieldSetFlags()[3] = true;
      }
      if (other.hasEntitiesBuilder()) {
        this.entitiesBuilder = tech.chengqu.avro.Entity.newBuilder(other.getEntitiesBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing Tweet instance
     * @param other The existing instance to copy.
     */
    private Builder(tech.chengqu.avro.Tweet other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.entities)) {
        this.entities = data().deepCopy(fields()[3].schema(), other.entities);
        fieldSetFlags()[3] = true;
      }
      this.entitiesBuilder = null;
    }

    /**
      * Gets the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @return The value.
      */
    public java.lang.CharSequence getUsername() {
      return username;
    }

    /**
      * Sets the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @param value The value of 'username'.
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder setUsername(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.username = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'username' field has been set.
      * Name of the user account on Twitter.com
      * @return True if the 'username' field has been set, false otherwise.
      */
    public boolean hasUsername() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'username' field.
      * Name of the user account on Twitter.com
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder clearUsername() {
      username = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'text' field.
      * The content of the user's Twitter message
      * @return The value.
      */
    public java.lang.CharSequence getText() {
      return text;
    }

    /**
      * Sets the value of the 'text' field.
      * The content of the user's Twitter message
      * @param value The value of 'text'.
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder setText(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.text = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'text' field has been set.
      * The content of the user's Twitter message
      * @return True if the 'text' field has been set, false otherwise.
      */
    public boolean hasText() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'text' field.
      * The content of the user's Twitter message
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder clearText() {
      text = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @return The value.
      */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }

    /**
      * Sets the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder setTimestamp(long value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * Unix epoch time in seconds
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * Unix epoch time in seconds
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder clearTimestamp() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'entities' field.
      * @return The value.
      */
    public tech.chengqu.avro.Entity getEntities() {
      return entities;
    }

    /**
      * Sets the value of the 'entities' field.
      * @param value The value of 'entities'.
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder setEntities(tech.chengqu.avro.Entity value) {
      validate(fields()[3], value);
      this.entitiesBuilder = null;
      this.entities = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'entities' field has been set.
      * @return True if the 'entities' field has been set, false otherwise.
      */
    public boolean hasEntities() {
      return fieldSetFlags()[3];
    }

    /**
     * Gets the Builder instance for the 'entities' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public tech.chengqu.avro.Entity.Builder getEntitiesBuilder() {
      if (entitiesBuilder == null) {
        if (hasEntities()) {
          setEntitiesBuilder(tech.chengqu.avro.Entity.newBuilder(entities));
        } else {
          setEntitiesBuilder(tech.chengqu.avro.Entity.newBuilder());
        }
      }
      return entitiesBuilder;
    }

    /**
     * Sets the Builder instance for the 'entities' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public tech.chengqu.avro.Tweet.Builder setEntitiesBuilder(tech.chengqu.avro.Entity.Builder value) {
      clearEntities();
      entitiesBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'entities' field has an active Builder instance
     * @return True if the 'entities' field has an active Builder instance
     */
    public boolean hasEntitiesBuilder() {
      return entitiesBuilder != null;
    }

    /**
      * Clears the value of the 'entities' field.
      * @return This builder.
      */
    public tech.chengqu.avro.Tweet.Builder clearEntities() {
      entities = null;
      entitiesBuilder = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Tweet build() {
      try {
        Tweet record = new Tweet();
        record.username = fieldSetFlags()[0] ? this.username : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.text = fieldSetFlags()[1] ? this.text : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (java.lang.Long) defaultValue(fields()[2]);
        if (entitiesBuilder != null) {
          record.entities = this.entitiesBuilder.build();
        } else {
          record.entities = fieldSetFlags()[3] ? this.entities : (tech.chengqu.avro.Entity) defaultValue(fields()[3]);
        }
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Tweet>
    WRITER$ = (org.apache.avro.io.DatumWriter<Tweet>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Tweet>
    READER$ = (org.apache.avro.io.DatumReader<Tweet>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
