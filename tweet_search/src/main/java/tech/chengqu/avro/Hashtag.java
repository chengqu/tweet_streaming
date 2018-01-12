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
@org.apache.avro.specific.AvroGenerated
public class Hashtag extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5901044960919548049L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Hashtag\",\"namespace\":\"tech.chengqu.avro\",\"fields\":[{\"name\":\"indices\",\"type\":\"int\"},{\"name\":\"text\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Hashtag> ENCODER =
      new BinaryMessageEncoder<Hashtag>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Hashtag> DECODER =
      new BinaryMessageDecoder<Hashtag>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<Hashtag> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<Hashtag> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Hashtag>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this Hashtag to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a Hashtag from a ByteBuffer. */
  public static Hashtag fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int indices;
  @Deprecated public java.lang.CharSequence text;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Hashtag() {}

  /**
   * All-args constructor.
   * @param indices The new value for indices
   * @param text The new value for text
   */
  public Hashtag(java.lang.Integer indices, java.lang.CharSequence text) {
    this.indices = indices;
    this.text = text;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return indices;
    case 1: return text;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: indices = (java.lang.Integer)value$; break;
    case 1: text = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'indices' field.
   * @return The value of the 'indices' field.
   */
  public java.lang.Integer getIndices() {
    return indices;
  }

  /**
   * Sets the value of the 'indices' field.
   * @param value the value to set.
   */
  public void setIndices(java.lang.Integer value) {
    this.indices = value;
  }

  /**
   * Gets the value of the 'text' field.
   * @return The value of the 'text' field.
   */
  public java.lang.CharSequence getText() {
    return text;
  }

  /**
   * Sets the value of the 'text' field.
   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
  }

  /**
   * Creates a new Hashtag RecordBuilder.
   * @return A new Hashtag RecordBuilder
   */
  public static tech.chengqu.avro.Hashtag.Builder newBuilder() {
    return new tech.chengqu.avro.Hashtag.Builder();
  }

  /**
   * Creates a new Hashtag RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Hashtag RecordBuilder
   */
  public static tech.chengqu.avro.Hashtag.Builder newBuilder(tech.chengqu.avro.Hashtag.Builder other) {
    return new tech.chengqu.avro.Hashtag.Builder(other);
  }

  /**
   * Creates a new Hashtag RecordBuilder by copying an existing Hashtag instance.
   * @param other The existing instance to copy.
   * @return A new Hashtag RecordBuilder
   */
  public static tech.chengqu.avro.Hashtag.Builder newBuilder(tech.chengqu.avro.Hashtag other) {
    return new tech.chengqu.avro.Hashtag.Builder(other);
  }

  /**
   * RecordBuilder for Hashtag instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Hashtag>
    implements org.apache.avro.data.RecordBuilder<Hashtag> {

    private int indices;
    private java.lang.CharSequence text;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(tech.chengqu.avro.Hashtag.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.indices)) {
        this.indices = data().deepCopy(fields()[0].schema(), other.indices);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing Hashtag instance
     * @param other The existing instance to copy.
     */
    private Builder(tech.chengqu.avro.Hashtag other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.indices)) {
        this.indices = data().deepCopy(fields()[0].schema(), other.indices);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'indices' field.
      * @return The value.
      */
    public java.lang.Integer getIndices() {
      return indices;
    }

    /**
      * Sets the value of the 'indices' field.
      * @param value The value of 'indices'.
      * @return This builder.
      */
    public tech.chengqu.avro.Hashtag.Builder setIndices(int value) {
      validate(fields()[0], value);
      this.indices = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'indices' field has been set.
      * @return True if the 'indices' field has been set, false otherwise.
      */
    public boolean hasIndices() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'indices' field.
      * @return This builder.
      */
    public tech.chengqu.avro.Hashtag.Builder clearIndices() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'text' field.
      * @return The value.
      */
    public java.lang.CharSequence getText() {
      return text;
    }

    /**
      * Sets the value of the 'text' field.
      * @param value The value of 'text'.
      * @return This builder.
      */
    public tech.chengqu.avro.Hashtag.Builder setText(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.text = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'text' field has been set.
      * @return True if the 'text' field has been set, false otherwise.
      */
    public boolean hasText() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'text' field.
      * @return This builder.
      */
    public tech.chengqu.avro.Hashtag.Builder clearText() {
      text = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Hashtag build() {
      try {
        Hashtag record = new Hashtag();
        record.indices = fieldSetFlags()[0] ? this.indices : (java.lang.Integer) defaultValue(fields()[0]);
        record.text = fieldSetFlags()[1] ? this.text : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Hashtag>
    WRITER$ = (org.apache.avro.io.DatumWriter<Hashtag>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Hashtag>
    READER$ = (org.apache.avro.io.DatumReader<Hashtag>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}