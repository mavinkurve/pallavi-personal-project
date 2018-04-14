import java.util.*;
import java.util.stream.Collectors;

public class GraphProblems {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        for (int j = 0; j < graph[0].length; j++) {
            paths.add(Arrays.asList(0, graph[0][j]));
        }
        if (paths.isEmpty())
                return null;
        for (int i = 1; i < graph.length; i++){
            List<List<Integer>> toRemove = new ArrayList<>();
            List<List<Integer>> toAdd = new ArrayList<>();
            for (List<Integer> path : paths) {
                if (path.get(path.size() - 1) == i) {
                    for (int j = 0; j < graph[i].length; j++) {
                        toRemove.add(path);
                        List<Integer> newPath = new ArrayList<>();
                         newPath.addAll(path);
                         newPath.add(graph[i][j]);
                         toAdd.add(newPath);
                    }
                }
            }
            paths.removeAll(toRemove);
            paths.addAll(toAdd);
        }
        return paths.stream().filter(p -> p.get(p.size() - 1).equals(graph.length - 1)).collect(Collectors.toList());
    }
}
