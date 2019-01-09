// Copyright 2018 Google LLC
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// [START program]
// [START import]
import static java.lang.Math.abs;
import com.google.ortools.constraintsolver.Assignment;
import com.google.ortools.constraintsolver.FirstSolutionStrategy;
import com.google.ortools.constraintsolver.LongLongToLong;
import com.google.ortools.constraintsolver.RoutingIndexManager;
import com.google.ortools.constraintsolver.RoutingModel;
import com.google.ortools.constraintsolver.RoutingSearchParameters;
import com.google.ortools.constraintsolver.main;
import java.util.logging.Logger;
// [END import]

/** Minimal TSP.*/
public class Tsp {
  static { System.loadLibrary("jniortools"); }

  private static final Logger logger = Logger.getLogger(Tsp.class.getName());

  // [START data_model]
  static class DataModel {
    public DataModel() {
      locations = new int[][] {
          {4, 4},
          {2, 0},
          {8, 0},
          {0, 1},
          {1, 1},
          {5, 2},
          {7, 2},
          {3, 3},
          {6, 3},
          {5, 5},
          {8, 5},
          {1, 6},
          {2, 6},
          {3, 7},
          {6, 7},
          {0, 8},
          {7, 8},
      };
      // Convert locations in meters using a city block dimension of 114m x 80m.
      for (int i = 0; i < locations.length; i++) {
        locations[i][0] *= 114;
        locations[i][1] *= 80;
      }
      vehicleNumber = 1;
      depot = 0;
    }
    public final int[][] locations;
    public final int vehicleNumber;
    public final int depot;
  }
  // [END data_model]

  // [START manhattan_distance]
  /// @brief Manhattan distance implemented as a callback.
  /// @details It uses an array of positions and computes
  /// the Manhattan distance between the two positions of
  /// two different indices.
  static class ManhattanDistance extends LongLongToLong {
    public ManhattanDistance(DataModel data, RoutingIndexManager manager) {
      // precompute distance between location to have distance callback in O(1)
      distanceMatrix_ = new long[data.locations.length][data.locations.length];
      indexManager_ = manager;
      for (int fromNode = 0; fromNode < data.locations.length; ++fromNode) {
        for (int toNode = 0; toNode < data.locations.length; ++toNode) {
          if (fromNode == toNode) {
            distanceMatrix_[fromNode][toNode] = 0;
          } else {
            distanceMatrix_[fromNode][toNode] =
                (long) abs(data.locations[toNode][0] - data.locations[fromNode][0])
                + (long) abs(data.locations[toNode][1] - data.locations[fromNode][1]);
          }
        }
      }
    }

    @Override
    public long run(long fromIndex, long toIndex) {
      int fromNode = indexManager_.indexToNode(fromIndex);
      int toNode = indexManager_.indexToNode(toIndex);
      return distanceMatrix_[fromNode][toNode];
    }
    private long[][] distanceMatrix_;
    private RoutingIndexManager indexManager_;
  }
  // [END manhattan_distance]

  // [START solution_printer]
  /// @brief Print the solution.
  static void printSolution(
      DataModel data, RoutingModel routing, RoutingIndexManager manager, Assignment solution) {
    // Solution cost.
    logger.info("Objective : " + solution.objectiveValue());
    // Inspect solution.
    logger.info("Route for Vehicle 0:");
    long routeDistance = 0;
    String route = "";
    long index = routing.start(0);
    while (!routing.isEnd(index)) {
      route += manager.indexToNode(index) + " -> ";
      long previousIndex = index;
      index = solution.value(routing.nextVar(index));
      routeDistance += routing.getArcCostForVehicle(previousIndex, index, 0);
    }
    route += manager.indexToNode(routing.end(0));
    logger.info(route);
    logger.info("Distance of the route: " + routeDistance + "m");
  }
  // [END solution_printer]

  public static void main(String[] args) throws Exception {
    // Instantiate the data problem.
    // [START data]
    final DataModel data = new DataModel();
    // [END data]

    // Create Routing Index Manager
    // [START index_manager]
    RoutingIndexManager manager =
        new RoutingIndexManager(data.locations.length, data.vehicleNumber, data.depot);
    // [END index_manager]

    // Create Routing Model.
    // [START routing_model]
    RoutingModel routing = new RoutingModel(manager);
    // [END routing_model]

    // Define cost of each arc.
    // [START arc_cost]
    LongLongToLong distanceEvaluator = new ManhattanDistance(data, manager);
    int transitCostIndex = routing.registerTransitCallback(distanceEvaluator);
    routing.setArcCostEvaluatorOfAllVehicles(transitCostIndex);
    // [END arc_cost]

    // Setting first solution heuristic.
    // [START parameters]
    RoutingSearchParameters searchParameters =
        main.defaultRoutingSearchParameters()
            .toBuilder()
            .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
            .build();
    // [END parameters]

    // Solve the problem.
    // [START solve]
    Assignment solution = routing.solveWithParameters(searchParameters);
    // [END solve]

    // Print solution on console.
    // [START print_solution]
    printSolution(data, routing, manager, solution);
    // [END print_solution]
  }
}
// [END program]