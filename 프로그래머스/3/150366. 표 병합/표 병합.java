import java.util.*;

class Solution {
    static Cell[][] sheet;
    static int idx;
    
    static class Cell {
        int idx;
        String value;
        
        public Cell (int idx, String value) {
            this.idx = idx;
            this.value = value;
        }
    }
    
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        
        sheet = new Cell[51][51];
        idx = 0;
        
        for (String command : commands) {
            String[] cmdArr = command.split(" ");
            String cmd = cmdArr[0];
            
            switch (cmd) {
                case "UPDATE":
                    if (cmdArr.length == 4) {
                        update(cmdArr[1], cmdArr[2], cmdArr[3]);
                    } else {
                        updateAll(cmdArr[1], cmdArr[2]);
                    }
                    break;
                case "MERGE":
                    merge(cmdArr[1], cmdArr[2], cmdArr[3], cmdArr[4]);
                    break;
                case "UNMERGE":
                    unMerge(cmdArr[1], cmdArr[2]);
                    break;
                case "PRINT":
                    String val = print(cmdArr[1], cmdArr[2]);
                    answer.add(val);
                    break;
            }
        }
        
        return answer.toArray(String[]::new);
    }
    
    
    static void update(String strR, String strC, String value) {
        int r = Integer.parseInt(strR);
        int c = Integer.parseInt(strC);
        
        if (sheet[r][c] != null) {
            sheet[r][c].value = value;
        } else {
            sheet[r][c] = new Cell(++idx, value);
        }
    }
    
    static void updateAll(String from, String to) {
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                if (sheet[r][c] != null && from.equals(sheet[r][c].value)) {
                    sheet[r][c].value = to;
                }
            }
        }
    }
    
    static void merge(String sr1, String sc1, String sr2, String sc2) {
        int r1 = Integer.parseInt(sr1);
        int c1 = Integer.parseInt(sc1);
        int r2 = Integer.parseInt(sr2);
        int c2 = Integer.parseInt(sc2);
        
        if (r1 == r2 && c1 == c2) {
            return;
        }
        
        Cell cell1 = sheet[r1][c1];
        Cell cell2 = sheet[r2][c2];
        
        if (cell1 == null && cell2 == null) {
            Cell cell = new Cell(++idx, "");
            sheet[r1][c1] = cell;
            sheet[r2][c2] = cell;
        } else if (isEmpty(cell1) && cell2 != null) {
            if (cell1 != null) {
                for (int r = 1; r <= 50; r++) {
                    for (int c = 1; c <= 50; c++) {
                        if (sheet[r][c] != null && sheet[r][c].idx == cell1.idx) {
                            sheet[r][c] = cell2;
                        }
                    }
                }
            } else {
                sheet[r1][c1] = cell2;
            }
        } else if (isEmpty(cell2) && cell1 != null) {
            if (cell2 != null) {
                for (int r = 1; r <= 50; r++) {
                    for (int c = 1; c <= 50; c++) {
                        if (sheet[r][c] != null && sheet[r][c].idx == cell2.idx) {
                            sheet[r][c] = cell1;
                        }
                    }
                }
            } else {
                sheet[r2][c2] = cell1;
            }
        } else {
            for (int r = 1; r <= 50; r++) {
                for (int c = 1; c <= 50; c++) {
                    if (sheet[r][c] != null && sheet[r][c].idx == cell2.idx) {
                        sheet[r][c] = cell1;
                    }
                }
            }
        }
    }
    
    static boolean isEmpty(Cell cell) {
        if (cell == null) {
            return true;
        }
        
        return cell.value.equals("");
    }
    
    static void unMerge(String sr, String sc) {
        int cr = Integer.parseInt(sr);
        int cc = Integer.parseInt(sc);
        
        Cell cell = sheet[cr][cc];
        
        if (cell == null) {
            return;
        }
        
        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                if (sheet[r][c] != null && sheet[r][c].idx == cell.idx) {
                    sheet[r][c] = null;
                }
            }
        }
        
        sheet[cr][cc] = cell;
    }
    
    static String print(String sr, String sc) {
        int r = Integer.parseInt(sr);
        int c = Integer.parseInt(sc);
        
        Cell cell = sheet[r][c];
        
        if (isEmpty(cell)) {
            return "EMPTY";
        }
        
        return cell.value;
    }
}