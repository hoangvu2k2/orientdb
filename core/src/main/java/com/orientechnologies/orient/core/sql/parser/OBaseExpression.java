/* Generated By:JJTree: Do not edit this line. OBaseExpression.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.collate.OCollate;
import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.exception.OCommandExecutionException;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OProperty;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.serialization.serializer.OStringSerializerHelper;
import com.orientechnologies.orient.core.sql.executor.AggregationContext;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultInternal;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OBaseExpression extends OMathExpression {

  protected ONumber number;

  protected OBaseIdentifier identifier;

  protected OInputParameter inputParam;

  protected String string;

  OModifier modifier;

  public OBaseExpression(int id) {
    super(id);
  }

  public OBaseExpression(OrientSql p, int id) {
    super(p, id);
  }

  public OBaseExpression(OIdentifier identifier) {
    super(-1);
    this.identifier = new OBaseIdentifier(identifier);
  }

  public OBaseExpression(String string) {
    super(-1);
    this.string = "\"" + OStringSerializerHelper.encode(string) + "\"";
  }

  public OBaseExpression(OIdentifier identifier, OModifier modifier) {
    this(identifier);
    if (modifier != null) {
      this.modifier = modifier;
    }
  }

  public OBaseExpression(ORecordAttribute attr, OModifier modifier) {
    super(-1);
    this.identifier = new OBaseIdentifier(attr);
    if (modifier != null) {
      this.modifier = modifier;
    }
  }

  /**
   * Accept the visitor.
   **/
  public Object jjtAccept(OrientSqlVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    if (number != null) {
      number.toString(params, builder);
    } else if (identifier != null) {
      identifier.toString(params, builder);
    } else if (string != null) {
      builder.append(string);
    } else if (inputParam != null) {
      inputParam.toString(params, builder);
    }

    if (modifier != null) {
      modifier.toString(params, builder);
    }

  }

  public Object execute(OIdentifiable iCurrentRecord, OCommandContext ctx) {
    Object result = null;
    if (number != null) {
      result = number.getValue();
    } else if (identifier != null) {
      result = identifier.execute(iCurrentRecord, ctx);
    } else if (string != null && string.length() > 1) {
      result = OStringSerializerHelper.decode(string.substring(1, string.length() - 1));
    } else if (inputParam != null) {
      result = inputParam.getValue(ctx.getInputParameters());
    }

    if (modifier != null) {
      result = modifier.execute(iCurrentRecord, result, ctx);
    }

    return result;
  }

  public Object execute(OResult iCurrentRecord, OCommandContext ctx) {
    Object result = null;
    if (number != null) {
      result = number.getValue();
    } else if (identifier != null) {
      result = identifier.execute(iCurrentRecord, ctx);
    } else if (string != null && string.length() > 1) {
      result = OStringSerializerHelper.decode(string.substring(1, string.length() - 1));
    } else if (inputParam != null) {
      result = inputParam.getValue(ctx.getInputParameters());
    }
    if (modifier != null) {
      result = modifier.execute(iCurrentRecord, result, ctx);
    }
    return result;
  }

  @Override
  protected boolean supportsBasicCalculation() {
    return true;
  }

  @Override
  public boolean isFunctionAny() {
    if (this.identifier == null) {
      return false;
    }
    return identifier.isFunctionAny();
  }

  @Override
  public boolean isIndexedFunctionCall() {
    if (this.identifier == null) {
      return false;
    }
    return identifier.isIndexedFunctionCall();
  }

  public long estimateIndexedFunction(OFromClause target, OCommandContext context, OBinaryCompareOperator operator, Object right) {
    if (this.identifier == null) {
      return -1;
    }
    return identifier.estimateIndexedFunction(target, context, operator, right);
  }

  public Iterable<OIdentifiable> executeIndexedFunction(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (this.identifier == null) {
      return null;
    }
    return identifier.executeIndexedFunction(target, context, operator, right);
  }

  /**
   * tests if current expression is an indexed funciton AND that function can also be executed without using the index
   *
   * @param target   the query target
   * @param context  the execution context
   * @param operator
   * @param right
   *
   * @return true if current expression is an indexed funciton AND that function can also be executed without using the index, false
   * otherwise
   */
  public boolean canExecuteIndexedFunctionWithoutIndex(OFromClause target, OCommandContext context, OBinaryCompareOperator operator,
      Object right) {
    if (this.identifier == null) {
      return false;
    }
    return identifier.canExecuteIndexedFunctionWithoutIndex(target, context, operator, right);
  }

  /**
   * tests if current expression is an indexed function AND that function can be used on this target
   *
   * @param target   the query target
   * @param context  the execution context
   * @param operator
   * @param right
   *
   * @return true if current expression is an indexed function AND that function can be used on this target, false otherwise
   */
  public boolean allowsIndexedFunctionExecutionOnTarget(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (this.identifier == null) {
      return false;
    }
    return identifier.allowsIndexedFunctionExecutionOnTarget(target, context, operator, right);
  }

  /**
   * tests if current expression is an indexed function AND the function has also to be executed after the index search. In some
   * cases, the index search is accurate, so this condition can be excluded from further evaluation. In other cases the result from
   * the index is a superset of the expected result, so the function has to be executed anyway for further filtering
   *
   * @param target  the query target
   * @param context the execution context
   *
   * @return true if current expression is an indexed function AND the function has also to be executed after the index search.
   */
  public boolean executeIndexedFunctionAfterIndexSearch(OFromClause target, OCommandContext context,
      OBinaryCompareOperator operator, Object right) {
    if (this.identifier == null) {
      return false;
    }
    return identifier.executeIndexedFunctionAfterIndexSearch(target, context, operator, right);
  }

  @Override
  public boolean isBaseIdentifier() {
    return identifier != null && modifier == null && identifier.isBaseIdentifier();
  }

  @Override
  public OCollate getCollate(OResult currentRecord, OCommandContext ctx) {
    return identifier != null && modifier == null ? identifier.getCollate(currentRecord, ctx) : null;
  }

  public boolean isEarlyCalculated(OCommandContext ctx) {
    if (number != null || inputParam != null || string != null) {
      return true;
    }
    if (identifier != null && identifier.isEarlyCalculated(ctx)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isExpand() {
    if (identifier != null) {
      return identifier.isExpand();
    }
    return false;
  }

  @Override
  public OExpression getExpandContent() {
    return this.identifier.getExpandContent();
  }

  public boolean needsAliases(Set<String> aliases) {
    if (this.identifier != null && this.identifier.needsAliases(aliases)) {
      return true;
    }
    if (modifier != null && modifier.needsAliases(aliases)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isAggregate() {
    if (identifier != null && identifier.isAggregate()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isCount() {
    if (identifier != null && identifier.isCount()) {
      return true;
    }
    return false;
  }

  public SimpleNode splitForAggregation(AggregateProjectionSplit aggregateProj, OCommandContext ctx) {
    if (isAggregate()) {
      SimpleNode splitResult = identifier.splitForAggregation(aggregateProj, ctx);
      if (splitResult instanceof OBaseIdentifier) {
        OBaseExpression result = new OBaseExpression(-1);
        result.identifier = (OBaseIdentifier) splitResult;
        return result;
      }
      return splitResult;
    } else {
      return this;
    }
  }

  public AggregationContext getAggregationContext(OCommandContext ctx) {
    if (identifier != null) {
      return identifier.getAggregationContext(ctx);
    } else {
      throw new OCommandExecutionException("cannot aggregate on " + toString());
    }
  }

  @Override
  public OBaseExpression copy() {
    OBaseExpression result = new OBaseExpression(-1);
    result.number = number == null ? null : number.copy();
    result.identifier = identifier == null ? null : identifier.copy();
    result.inputParam = inputParam == null ? null : inputParam.copy();
    result.string = string;
    result.modifier = modifier == null ? null : modifier.copy();
    return result;
  }

  public boolean refersToParent() {
    if (identifier != null && identifier.refersToParent()) {
      return true;
    }
    if (modifier != null && modifier.refersToParent()) {
      return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OBaseExpression that = (OBaseExpression) o;

    if (number != null ? !number.equals(that.number) : that.number != null)
      return false;
    if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
      return false;
    if (inputParam != null ? !inputParam.equals(that.inputParam) : that.inputParam != null)
      return false;
    if (string != null ? !string.equals(that.string) : that.string != null)
      return false;
    if (modifier != null ? !modifier.equals(that.modifier) : that.modifier != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = number != null ? number.hashCode() : 0;
    result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
    result = 31 * result + (inputParam != null ? inputParam.hashCode() : 0);
    result = 31 * result + (string != null ? string.hashCode() : 0);
    result = 31 * result + (modifier != null ? modifier.hashCode() : 0);
    return result;
  }

  public void setIdentifier(OBaseIdentifier identifier) {
    this.identifier = identifier;
  }

  public OBaseIdentifier getIdentifier() {
    return identifier;
  }

  public OModifier getModifier() {
    return modifier;
  }

  public List<String> getMatchPatternInvolvedAliases() {
    if (this.identifier != null && this.identifier.toString().equals("$matched")) {
      if (modifier != null && modifier.suffix != null && modifier.suffix.identifier != null) {
        return Collections.singletonList(modifier.suffix.identifier.toString());
      }
    }
    return null;
  }

  @Override
  public void applyRemove(OResultInternal result, OCommandContext ctx) {
    if (identifier != null) {
      if (modifier == null) {
        identifier.applyRemove(result, ctx);
      } else {
        Object val = identifier.execute(result, ctx);
        modifier.applyRemove(val, result, ctx);
      }
    }
  }

  public OResult serialize() {
    OResultInternal result = (OResultInternal) super.serialize();

    if (number != null) {
      result.setProperty("number", number.serialize());
    }
    if (identifier != null) {
      result.setProperty("identifier", identifier.serialize());
    }
    if (inputParam != null) {
      result.setProperty("inputParam", inputParam.serialize());
    }
    if (string != null) {
      result.setProperty("string", string);
    }
    if (modifier != null) {
      result.setProperty("modifier", modifier.serialize());
    }
    return result;
  }

  public void deserialize(OResult fromResult) {
    super.deserialize(fromResult);

    if (fromResult.getProperty("number") != null) {
      number = new ONumber(-1);
      number.deserialize(fromResult.getProperty("number"));
    }
    if (fromResult.getProperty("identifier") != null) {
      identifier = new OBaseIdentifier(-1);
      identifier.deserialize(fromResult.getProperty("identifier"));
    }
    if (fromResult.getProperty("inputParam") != null) {
      inputParam = OInputParameter.deserializeFromOResult(fromResult.getProperty("inputParam"));
    }

    if (fromResult.getProperty("string") != null) {
      string = fromResult.getProperty("string");
    }
    if (fromResult.getProperty("modifier") != null) {
      modifier = new OModifier(-1);
      modifier.deserialize(fromResult.getProperty("modifier"));
    }
  }

  @Override
  public boolean isDefinedFor(OResult currentRecord) {
    if (this.identifier != null) {
      if (modifier == null) {
        return identifier.isDefinedFor(currentRecord);
      }

    }
    return true;

  }

  @Override
  public boolean isDefinedFor(OElement currentRecord) {
    if (this.identifier != null) {
      if (modifier == null) {
        return identifier.isDefinedFor(currentRecord);
      }

    }
    return true;
  }

  public void extractSubQueries(OIdentifier letAlias, SubQueryCollector collector) {
    if (this.identifier != null) {
      this.identifier.extractSubQueries(letAlias, collector);
    }
  }

  public void extractSubQueries(SubQueryCollector collector) {
    if (this.identifier != null) {
      this.identifier.extractSubQueries(collector);
    }
  }

  public boolean isCacheable() {
    if (modifier != null && !modifier.isCacheable()) {
      return false;
    }
    if (identifier != null) {
      return identifier.isCacheable();
    }

    return true;
  }

  public void setInputParam(OInputParameter inputParam) {
    this.inputParam = inputParam;
  }

  public boolean isIndexChain(OCommandContext ctx, OClass clazz) {
    if (modifier == null) {
      return false;
    }
    if (identifier.isIndexChain(ctx, clazz)) {
      OProperty prop = clazz.getProperty(identifier.getSuffix().identifier.getStringValue());
      OClass linkedClass = prop.getLinkedClass();
      if (linkedClass != null) {
        return modifier.isIndexChain(ctx, linkedClass);
      }
    }
    return false;
  }

}

/* JavaCC - OriginalChecksum=71b3e2d1b65c923dc7cfe11f9f449d2b (do not edit this line) */
