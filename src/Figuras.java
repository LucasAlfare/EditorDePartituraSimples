import java.awt.geom.Path2D;

public class Figuras {

    public static void claveDeSol(Path2D painter, int x, int y, float escala){
        String pathClaveDeSol = "m51.688 5.25c-5.427-0.1409-11.774 12.818-11.563 24.375 0.049 3.52 1.16 10.659 2.781 19.625-10.223 10.581-22.094 21.44-22.094 35.688-0.163 13.057 7.817 29.692 26.75 29.532 2.906-0.02 5.521-0.38 7.844-1 1.731 9.49 2.882 16.98 2.875 20.44 0.061 13.64-17.86 14.99-18.719 7.15 3.777-0.13 6.782-3.13 6.782-6.84 0-3.79-3.138-6.88-7.032-6.88-2.141 0-4.049 0.94-5.343 2.41-0.03 0.03-0.065 0.06-0.094 0.09-0.292 0.31-0.538 0.68-0.781 1.1-0.798 1.35-1.316 3.29-1.344 6.06 0 11.42 28.875 18.77 28.875-3.75 0.045-3.03-1.258-10.72-3.156-20.41 20.603-7.45 15.427-38.04-3.531-38.184-1.47 0.015-2.887 0.186-4.25 0.532-1.08-5.197-2.122-10.241-3.032-14.876 7.199-7.071 13.485-16.224 13.344-33.093 0.022-12.114-4.014-21.828-8.312-21.969zm1.281 11.719c2.456-0.237 4.406 2.043 4.406 7.062 0.199 8.62-5.84 16.148-13.031 23.719-0.688-4.147-1.139-7.507-1.188-9.5 0.204-13.466 5.719-20.886 9.813-21.281zm-7.719 44.687c0.877 4.515 1.824 9.272 2.781 14.063-12.548 4.464-18.57 21.954-0.781 29.781-10.843-9.231-5.506-20.158 2.312-22.062 1.966 9.816 3.886 19.502 5.438 27.872-2.107 0.74-4.566 1.17-7.438 1.19-7.181 0-21.531-4.57-21.531-21.875 0-14.494 10.047-20.384 19.219-28.969zm6.094 21.469c0.313-0.019 0.652-0.011 0.968 0 13.063 0 17.99 20.745 4.688 27.375-1.655-8.32-3.662-17.86-5.656-27.375z";
        executarPath(pathClaveDeSol, painter, x, y, escala);
    }

    private static void executarPath(String pathRelativo, Path2D painter, int x, int y, float escala){
        Conversor c = new Conversor();
        c.relativoParaAbsoluto(pathRelativo);

        for (Conversor.OperacaoSVG absSvgPath : c.operacoesAbsolutas){
            switch (absSvgPath.comando.toLowerCase()){
                case "m":
                    painter.moveTo(
                            (absSvgPath.valores.get(0) + x) * escala,
                            (absSvgPath.valores.get(1) + y) * escala
                    );
                    break;
                case "c":
                    painter.curveTo(
                            (absSvgPath.valores.get(0) + x) * escala, (absSvgPath.valores.get(1) + y) * escala,
                            (absSvgPath.valores.get(2) + x) * escala, (absSvgPath.valores.get(3) + y) * escala,
                            (absSvgPath.valores.get(4) + x) * escala, (absSvgPath.valores.get(5) + y) * escala
                    );
                    break;
                case "z":
                    painter.closePath();
                    break;
            }
        }
    }
}
