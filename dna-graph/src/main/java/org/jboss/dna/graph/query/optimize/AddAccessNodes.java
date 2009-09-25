/*
 * JBoss DNA (http://www.jboss.org/dna)
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * See the AUTHORS.txt file in the distribution for a full listing of 
 * individual contributors.
 *
 * JBoss DNA is free software. Unless otherwise indicated, all code in JBoss DNA
 * is licensed to you under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * 
 * JBoss DNA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.graph.query.optimize;

import java.util.LinkedList;
import net.jcip.annotations.Immutable;
import org.jboss.dna.graph.query.QueryContext;
import org.jboss.dna.graph.query.plan.CanonicalPlanner;
import org.jboss.dna.graph.query.plan.PlanHints;
import org.jboss.dna.graph.query.plan.PlanNode;
import org.jboss.dna.graph.query.plan.PlanNode.Type;

/**
 * An {@link OptimizerRule optimizer rule} that inserts an ACCESS above each SOURCE node in a query plan. This rule is often the
 * first rule to run against a {@link CanonicalPlanner canonical plan} (see
 * {@link RuleBasedOptimizer#populateRuleStack(LinkedList, PlanHints)}.
 * <p>
 * Before:
 * 
 * <pre>
 *        ...
 *         |
 *       SOURCE
 * </pre>
 * 
 * After:
 * 
 * <pre>
 *        ...
 *         |
 *       ACCESS
 *         |
 *       SOURCE
 * </pre>
 * 
 * </p>
 */
@Immutable
public class AddAccessNodes implements OptimizerRule {

    public static final AddAccessNodes INSTANCE = new AddAccessNodes();

    /**
     * {@inheritDoc}
     * 
     * @see org.jboss.dna.graph.query.optimize.OptimizerRule#execute(org.jboss.dna.graph.query.QueryContext,
     *      org.jboss.dna.graph.query.plan.PlanNode, java.util.LinkedList)
     */
    public PlanNode execute( QueryContext context,
                             PlanNode plan,
                             LinkedList<OptimizerRule> ruleStack ) {
        // On each of the source nodes ...
        for (PlanNode source : plan.findAllAtOrBelow(Type.SOURCE)) {
            // The source node should have no children ...
            if (source.getChildCount() != 0) continue;

            // Create the ACCESS node, set the selectors, and insert above the source node ...
            PlanNode access = new PlanNode(Type.ACCESS);
            access.addSelectors(source.getSelectors());
            source.insertAsParent(access);
        }
        return plan;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}