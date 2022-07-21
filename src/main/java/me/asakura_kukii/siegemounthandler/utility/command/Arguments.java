package me.asakura_kukii.siegemounthandler.utility.command;

public class Arguments {
    int current = 0;
    private String[] args;

    public Arguments(String[] args){
        this.args = args;
    }

    public boolean hasNext(){
        return current < args.length;
    }

    public int nextInt(){
        try{
            String arg = args[current++];
            return Integer.parseInt(arg);
        }catch (Exception e){
            current --;
            throw new BadCommandException("int", e);
        }
    }

    public double nextDouble(){
        try{
            String arg = args[current++];
            return Double.parseDouble(arg);
        }catch (Exception e){
            current --;
            throw new BadCommandException("double", e);
        }
    }

    public long nextLong(){
        try{
            String arg = args[current++];
            return Long.parseLong(arg);
        }catch (Exception e){
            current --;
            throw new BadCommandException("long", e);
        }
    }

    public boolean nextBoolean(){
        try{
            String arg = args[current++];
            return Boolean.parseBoolean(arg);
        }catch (Exception e){
            current --;
            throw new BadCommandException("boolean", e);
        }
    }

    public String nextString(){
        try{
            String arg = args[current++];
            return arg;
        }catch (Exception e){
            current --;
            throw new BadCommandException("String", e);
        }
    }

    public String peek() {
        return args[current];
    }
}
