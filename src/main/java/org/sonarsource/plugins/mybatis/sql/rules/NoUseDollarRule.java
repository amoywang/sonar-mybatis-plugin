package org.sonarsource.plugins.mybatis.sql.rules;

import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import org.sonar.api.rule.Severity;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;
import org.sonarsource.plugins.mybatis.sql.Constant;

import java.util.Objects;

public class NoUseDollarRule extends AbstractRule {
    @Override
    public boolean visit(SQLMethodInvokeExpr x) {
        String methodName = x.getMethodName();
        if (Objects.equals(methodName, "$")) {
            this.addCheckResult(x);
        }
        return super.visit(x);
    }

    @Override
    public String getRuleID() {
        return Constant.MYBATIS_MATTER_CHECK_RULE_PREFIX + this.getClass().getSimpleName();
    }

    @Override
    public String getSeverity() {
        return Severity.BLOCKER;
    }

    @Override
    public String getName() {
        return "参数必须使用#{}，不允许使用${}";
    }

    @Override
    public String getDescription() {
        return "禁止使用${param}方式进行参数预占，会造成SQL注入攻击";
    }

    @Override
    public String getSimpleDescription() {
        return "参数必须使用#{}，不允许使用${}";
    }
}
