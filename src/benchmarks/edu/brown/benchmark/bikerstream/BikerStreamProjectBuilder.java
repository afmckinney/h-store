/***************************************************************************
 *  Copyright (C) 2012 by H-Store Project                                  *
 *  Brown University                                                       *
 *  Massachusetts Institute of Technology                                  *
 *  Yale University                                                        *
 *                                                                         *
 *  Coded By:  Justin A. DeBrabant (http://www.cs.brown.edu/~debrabant/)   *
 *                                                                         *
 *                                                                         *
 *  Permission is hereby granted, free of charge, to any person obtaining  *
 *  a copy of this software and associated documentation files (the        *
 *  "Software"), to deal in the Software without restriction, including    *
 *  without limitation the rights to use, copy, modify, merge, publish,    *
 *  distribute, sublicense, and/or sell copies of the Software, and to     *
 *  permit persons to whom the Software is furnished to do so, subject to  *
 *  the following conditions:                                              *
 *                                                                         *
 *  The above copyright notice and this permission notice shall be         *
 *  included in all copies or substantial portions of the Software.        *
 *                                                                         *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,        *
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF     *
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. *
 *  IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR      *
 *  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,  *
 *  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR  *
 *  OTHER DEALINGS IN THE SOFTWARE.                                        *
 ***************************************************************************/

package edu.brown.benchmark.bikerstream;

import org.voltdb.VoltProcedure;

import edu.brown.benchmark.AbstractProjectBuilder;
import edu.brown.api.BenchmarkComponent;

import edu.brown.benchmark.bikerstream.procedures.Initialize;
import edu.brown.benchmark.bikerstream.procedures.SignUp;
import edu.brown.benchmark.bikerstream.procedures.CheckoutBike;
import edu.brown.benchmark.bikerstream.procedures.CheckinBike;
//import edu.brown.benchmark.bikerstream.procedures.InsertIntoBikeReadingsTableWinTrigger;
//import edu.brown.benchmark.bikerstream.procedures.RideBike;

public class BikerStreamProjectBuilder extends AbstractProjectBuilder {

    // REQUIRED: Retrieved via reflection by BenchmarkController
    public static final Class<? extends BenchmarkComponent> m_clientClass = BikerStreamClient.class;

    // REQUIRED: Retrieved via reflection by BenchmarkController
    public static final Class<? extends BenchmarkComponent> m_loaderClass = BikerStreamLoader.class;

    // a list of procedures implemented in this benchmark
    @SuppressWarnings("unchecked")
    public static final Class<? extends VoltProcedure> PROCEDURES[] = (Class<? extends VoltProcedure>[])new Class<?>[]{
        Initialize.class,
        SignUp.class,
        CheckoutBike.class,
        CheckinBike.class,
//        RideBike.class,
//        InsertIntoBikeReadingsTableWinTrigger.class,
    };

    {
    // nothing to do here - code saved as
    // placeholder/reminder
        //addTransactionFrequency(Vote.class, 100);
    }

    // a list of tables used in this benchmark with corresponding partitioning keys
    public static final String PARTITIONING[][] = new String[][] {
        { "bikereadings_table", "bike_id" },
        { "bikereadings_stream", "bike_id"},
        // TODO - do windows have partitioning??
        { "bikereadings_window_rows", "bike_id"},
        { "count_bikereadings_table", "bike_id"},
    };

    public BikerStreamProjectBuilder() {
        super("bikerstream", BikerStreamProjectBuilder.class, PROCEDURES, PARTITIONING);
    }
}




