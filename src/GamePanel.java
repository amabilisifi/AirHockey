import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public String myPlayerName;
    public String otherPlayerName;
    public boolean timeLimitedMode;
    public int LimitTime;
    public boolean goalLimitedMode;
    public int LimitGoal;
    public boolean twoMarginMode;
    public int width = 499;
    public int height = 701;
    public int puckSize = 60;
    public int deltaX = 17;
    public int deltaY = 20;
    public double xMyPuck = width / 2.0 - puckSize / 2.0;
    public double yMyPuck = (int) (height - puckSize - 1.5 * deltaY);
    public double xOtherPuck = width / 2.0 - puckSize / 2.0;
    public double yOtherPuck = puckSize / 2.0;
    public int ballSize = 40;
    public double xBall = width / 2.0 - ballSize / 2.0;
    public double yBall = height / 2.0 - ballSize / 2.0;
    public double xVelocity = Math.random() * 3 + 2;
    public double yVelocity = Math.random() * 2 + 2;
    Timer timer = new Timer(10, this);
    double ballRadios = ballSize / 2.0;
    double puckRadios = puckSize / 2.0;
    double myGateRadios = 3 * puckRadios;
    double otherGateRadios = 3 * puckRadios;
    double xMyMinCritical = width / 2.0 - myGateRadios;
    double xMyMaxCritical = width / 2.0 + myGateRadios;
    double xOtherMinCritical = width / 2.0 - otherGateRadios;
    double xOtherMaxCritical = width / 2.0 + otherGateRadios;
    public int myScore = 0;
    public int otherScore = 0;
    ZonedDateTime now = ZonedDateTime.now();
    public boolean isPaused = false;
    public boolean bonusAppearance;
    public boolean bonusPlaceIsSet = true;
    public boolean bonusActivated;
    public int bonusRadios = 15;
    public double xBonusCenter;
    public double yBonusCenter;
    public long bonusAppearTime;
    public long lastBonusFadeTime;
    public boolean bonusFirstExpand;
    public boolean bonusSecondExpand;
    public boolean goalAfter;
    public int lastStriker;
    // 1 is myPuck 2 is otherPuck
    public int bonusType;
    // 1 is fireBall:   X2 ball velocity & collision
    // 2 is biggerGate
    // 3 is mirrorWall : IDK what the hell is that
    public boolean typeSet;
    public boolean skipMyCollision;
    public boolean skipOtherCollision;
    public boolean doneOnce;
    public boolean mirrorWall;
    public boolean ableToMoveMyPuck = true;
    public boolean ableToMoveOtherPuck = true;
    public boolean myOnce;
    public boolean otherOnce;
    long pausedTime = 0;
    long pauseStartTime;
    long pauseEndTime;

    GamePanel(String myPlayerName, String otherPlayerName, boolean timeLimitedMode, int limitTime, boolean goalLimitedMode, int limitGoal, boolean twoMarginMode) {
        timer.start();
        this.setPreferredSize(new Dimension(499, 701));
        this.myPlayerName = myPlayerName;
        this.otherPlayerName = otherPlayerName;
        this.timeLimitedMode = timeLimitedMode;
        this.LimitTime = limitTime;
        this.goalLimitedMode = goalLimitedMode;
        this.LimitGoal = limitGoal;
        this.twoMarginMode = twoMarginMode;
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(new Color(10, 3, 40));
        g2D.fillRect(0, 0, width, height);
        g2D.setPaint(new Color(239, 147, 109));
        g2D.setStroke(new BasicStroke(3));
        g2D.drawRect(deltaX, deltaY, (int) (width - 2 * deltaX), (int) (height - 2 * deltaY));
        g2D.drawArc((int) (width / 2 - otherGateRadios), (int) (deltaY - otherGateRadios), (int) (2 * otherGateRadios), (int) (2 * otherGateRadios), 180, 180);
        g2D.drawArc((int) (width / 2 - myGateRadios), (int) (height - myGateRadios - deltaY), (int) (2 * myGateRadios), (int) (2 * myGateRadios), 0, 180);
        g2D.setStroke(new BasicStroke(7));
        g2D.drawLine((int) xOtherMaxCritical, deltaY, (int) xOtherMinCritical, deltaY);
        g2D.drawLine((int) xMyMaxCritical, (int) (height - deltaY), (int) xMyMinCritical, (int) (height - deltaY));
        g2D.setPaint(new Color(239, 198, 109));
        g2D.setStroke(new BasicStroke(2));
        g2D.fillOval(width / 2 - 7, height / 2 - 13, 10, 10);
        g2D.drawOval(width / 2 - 52, height / 2 - 57, 100, 100);
        g2D.drawLine(deltaX, (height - deltaY) / 2, width - deltaX, (height - deltaY) / 2);
        g2D.setPaint(new Color(103, 220, 167));
        g2D.fillOval((int) xMyPuck, (int) yMyPuck, puckSize, puckSize);
        g2D.setFont(new Font("Ink Free", Font.BOLD, 40));
        g2D.drawString(String.valueOf(myScore), (int) width / 2 - 20, (int) 3 * height / 4 - 20);
        g2D.setPaint(new Color(126, 181, 236));
        g2D.fillOval((int) xOtherPuck, (int) yOtherPuck, puckSize, puckSize);
        g2D.setFont(new Font("Ink Free", Font.BOLD, 40));
        g2D.drawString(String.valueOf(otherScore), (int) width / 2 - 20, (int) height / 4 + 20);
        g2D.setPaint(new Color(168, 109, 175));
        g2D.fillOval((int) xBall, (int) yBall, ballSize, ballSize);
        g2D.setPaint(Color.WHITE);
        g2D.setFont(new Font("Ink Free", Font.ITALIC, 25));
        g2D.setPaint(new Color(195, 232, 130));
        g2D.drawString(myPlayerName, width - 123, height - deltaY - 17);
        g2D.drawString(otherPlayerName, deltaX + 30, deltaY + 30);
        g2D.setPaint(new Color(124, 111, 243));
        long seconds = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS) - pausedTime;
        if (!timeLimitedMode)
            g2D.drawString(seconds / 60 + " : " + String.valueOf(seconds - (seconds / 60) * 60), width / 2 - 2 * deltaX, height / 2 + deltaY);
        if (timeLimitedMode) {
            long time = LimitTime - seconds;
            if (time >= 0)
                g2D.drawString(time / 60 + " : " + String.valueOf(time - (time / 60) * 60), width / 2 - 2 * deltaX, height / 2 + deltaY);
            if (time < 0)
                g2D.drawString("0 : 0", width / 2 - 2 * deltaX, height / 2 + deltaY);
        }

        long time = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
        if (time == 10 || time - lastBonusFadeTime == 10) {
            bonusAppearance = true;
            bonusActivated = false;
            goalAfter = false;
            AppearTheBonus();
        }

        if (bonusAppearance) {
            if (bonusType == 1)
                g2D.setPaint(new Color(255, 0, 0));
            if (bonusType == 2)
                g2D.setPaint(new Color(221, 106, 54));
            if (bonusType == 3)
                g2D.setPaint(new Color(136, 132, 123));
            g2D.drawOval((int) (xBonusCenter - bonusRadios), (int) (yBonusCenter - bonusRadios), 2 * bonusRadios, 2 * bonusRadios);
            bonusExistence();
        }
        if (!bonusAppearance) {
            bonusPlaceIsSet = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean My = true;
        if (My) {
            if (e.getKeyCode() == 37) {
                if (xMyPuck > deltaX)
                    xMyPuck -= deltaX;
                ableToMoveMyPuck = true;
                repaint();
            }
            if (e.getKeyCode() == 38) {
                if (yMyPuck - deltaY > height / 2)
                    yMyPuck -= deltaY;
                ableToMoveMyPuck = true;
                repaint();
            }
            if (e.getKeyCode() == 39) {
                if (xMyPuck + deltaX + 1.1 * puckSize <= width)
                    xMyPuck += deltaX;
                ableToMoveMyPuck = true;
                repaint();
            }
            if (e.getKeyCode() == 40) {
                if (yMyPuck + deltaY + 1.5 * puckSize <= height)
                    yMyPuck += deltaY;
                ableToMoveMyPuck = true;
                repaint();
            }
        }
        boolean Other = true;
        if (Other) {
            if (e.getKeyChar() == 'a') {
                if (xOtherPuck > deltaX)
                    xOtherPuck -= deltaX;
                ableToMoveOtherPuck = true;
                repaint();
            }
            if (e.getKeyChar() == 'w') {
                if (yOtherPuck > deltaY)
                    yOtherPuck -= deltaY;
                ableToMoveOtherPuck = true;
                repaint();
            }
            if (e.getKeyChar() == 'd') {
                if (xOtherPuck + deltaX + 1.1 * puckSize <= width)
                    xOtherPuck += deltaX;
                ableToMoveOtherPuck = true;
                repaint();
            }
            if (e.getKeyChar() == 's') {
                if (yOtherPuck + deltaY + puckSize < height / 2)
                    yOtherPuck += deltaY;
                ableToMoveOtherPuck = true;
                repaint();
            }
        }
        if (e.getKeyChar() == 'p') {
            if (!isPaused) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "your game is paused enter p again to play.");
                isPaused = true;
                pauseStartTime = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
            } else {
                isPaused = false;
                timer.start();
                pauseEndTime = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
                pausedTime += pauseEndTime - pauseStartTime;
            }
        }
        if (e.getKeyChar() == 'q') {
            System.out.println(skipOtherCollision);
        }
        if (e.getKeyChar() == 'e') {
            myGateRadios = 3 * puckRadios;
            otherGateRadios = 3 * puckRadios;
            xMyMinCritical = width / 2.0 - myGateRadios;
            xMyMaxCritical = width / 2.0 + myGateRadios;
            xOtherMinCritical = width / 2.0 - otherGateRadios;
            xOtherMaxCritical = width / 2.0 + otherGateRadios;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        xBall += xVelocity;
        yBall += yVelocity;

        repaint();
        double xBallCenter = xBall + ballSize / 2.0;
        double yBallCenter = yBall + ballSize / 2.0;
        double xMyPuckCenter = xMyPuck + puckSize / 2.0;
        double yMyPuckCenter = yMyPuck + puckSize / 2.0;
        double xOtherPuckCenter = xOtherPuck + puckSize / 2.0;
        double yOtherPuckCenter = yOtherPuck + puckSize / 2.0;
        if (collisionTwoCircle(xBonusCenter, yBonusCenter, bonusRadios, xBallCenter, yBallCenter, ballRadios) && bonusAppearance) {
            bonusActivated = true;
            bonusAppearance = false;
            lastBonusFadeTime = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
            ActiveTheBonus();
            FadeTheBonus();
        }
        if (bonusActivated && !goalAfter) {
            /** bonus action **/
            if (bonusType == 1) {
                if (lastStriker == 1) {
                    skipOtherCollision = true;
                }
                if (lastStriker == 2) {
                    skipMyCollision = true;
                }
                if (!doneOnce) {
                    xVelocity *= 2;
                    yVelocity *= 2;
                    doneOnce = true;
                }
            }
            if (bonusType == 2 && !doneOnce) {
                if (lastStriker == 1) {
                    otherGateRadios *= 1.2;
                }
                if (lastStriker == 2) {
                    myGateRadios *= 1.2;
                }
                xMyMinCritical = width / 2.0 - myGateRadios;
                xMyMaxCritical = width / 2.0 + myGateRadios;
                xOtherMinCritical = width / 2.0 - otherGateRadios;
                xOtherMaxCritical = width / 2.0 + otherGateRadios;
                doneOnce = true;
            }
            if (bonusType == 3) {
                mirrorWall = true;
            }
        }
        if (bonusActivated && goalAfter) {
            bonusActivated = false;
            ballRadios = 20;
            if (bonusType == 1) {
                xVelocity /= 2;
                yVelocity /= 2;
            }
            skipMyCollision = false;
            skipOtherCollision = false;
            myGateRadios = 3 * puckRadios;
            otherGateRadios = 3 * puckRadios;
            xMyMinCritical = width / 2.0 - myGateRadios;
            xMyMaxCritical = width / 2.0 + myGateRadios;
            xOtherMinCritical = width / 2.0 - otherGateRadios;
            xOtherMaxCritical = width / 2.0 + otherGateRadios;
            mirrorWall = false;
            typeSet = false;
        }
        checkGoal(xBallCenter);
        checkWinner();
        if (collisionTwoCircle(xMyPuckCenter, yMyPuckCenter, puckRadios, xBallCenter, yBallCenter, ballRadios + 1) && !skipMyCollision)
            ableToMoveMyPuck = false;
        if (!collisionTwoCircle(xMyPuckCenter, yMyPuckCenter, puckRadios, xBallCenter, yBallCenter, ballRadios + 1))
            myOnce = false;
        if (collisionTwoCircle(xMyPuckCenter, yMyPuckCenter, puckRadios, xBallCenter, yBallCenter, ballRadios) && !skipMyCollision) {
            if (!bonusActivated) {
                lastStriker = 1;
            }
            /**boolean right = (int) (xBallCenter) - (int) (yMyPuckCenter) > 0;
             boolean up = (int) (yMyPuckCenter) - (int) (yBallCenter) > 0;
             if (right && up) {
             xVelocity = Math.abs(xVelocity) * +1;
             yVelocity = Math.abs(yVelocity) * -1;
             }
             if (right && !up) {
             xVelocity = Math.abs(xVelocity) * +1;
             yVelocity = Math.abs(yVelocity) * +1;
             }
             if (!right && up) {
             xVelocity = Math.abs(xVelocity) * -1;
             yVelocity = Math.abs(yVelocity) * -1;
             }
             if (!right && !up) {
             xVelocity = Math.abs(xVelocity) * -1;
             yVelocity = Math.abs(yVelocity) * +1;
             }**/
            if (!myOnce) {
                double collisionAngle = Math.atan2(yMyPuckCenter - yBallCenter, xMyPuckCenter - xBallCenter);
                xVelocity = Math.cos(collisionAngle) * xVelocity + Math.sin(collisionAngle) * yVelocity;
                yVelocity = -Math.sin(collisionAngle) * xVelocity + Math.cos(collisionAngle) * yVelocity;
                if (Math.abs(xVelocity) > 9) {
                    xVelocity /= 2;
                }
                if (Math.abs(yVelocity) > 9) {
                    yVelocity /= 2;
                }
                if (Math.abs(xVelocity) <= 9 && Math.abs(xVelocity) > 6) {
                    xVelocity -= 3;
                }
                if (Math.abs(yVelocity) <= 9 && Math.abs(yVelocity) > 6) {
                    yVelocity -= 3;
                }
                boolean right = (int) (xBallCenter) - (int) (yMyPuckCenter) > 0;
                boolean up = (int) (yMyPuckCenter) - (int) (yBallCenter) > 0;
                xVelocity = Math.random() * 2 + 2;
                yVelocity = Math.random() + 2;
                if (right && up) {
                    xVelocity = Math.abs(xVelocity) * 1;
                    yVelocity = Math.abs(yVelocity) * -1;
                }
                if (right && !up) {
                    xVelocity = Math.abs(xVelocity) * +1;
                    yVelocity = Math.abs(yVelocity) * +1;
                }
                if (!right && up) {
                    xVelocity = Math.abs(xVelocity) * -1;
                    yVelocity = Math.abs(yVelocity) * -1;
                }
                if (!right && !up) {
                    xVelocity = Math.abs(xVelocity) * -1;
                    yVelocity = Math.abs(yVelocity) * +1;
                }
                myOnce = true;
            }
        }
        if (collisionTwoCircle(xOtherPuckCenter, yOtherPuckCenter, puckRadios, xBallCenter, yBallCenter, ballRadios + 1) && !skipOtherCollision)
            ableToMoveOtherPuck = false;
        if (!collisionTwoCircle(xOtherPuckCenter, yOtherPuckCenter, puckRadios, xBallCenter, yBallCenter, ballRadios + 1))
            otherOnce = false;

        if ((Math.pow((xOtherPuckCenter - xBallCenter), 2) + Math.pow(yOtherPuckCenter - yBallCenter, 2) <= Math.pow(ballRadios + puckRadios, 2)) && !skipOtherCollision) {
            if (!bonusActivated) {
                lastStriker = 2;
            }
            if (!otherOnce) {
                double collisionAngle = Math.atan2(yOtherPuckCenter - yBallCenter, xOtherPuckCenter - xBallCenter);
                xVelocity = Math.cos(collisionAngle) * xVelocity + Math.sin(collisionAngle) * yVelocity;
                yVelocity = -Math.sin(collisionAngle) * xVelocity + Math.cos(collisionAngle) * yVelocity;
                if (Math.abs(xVelocity) > 9) {
                    xVelocity /= 2;
                }
                if (Math.abs(yVelocity) > 9) {
                    yVelocity /= 2;
                }
                if (Math.abs(xVelocity) <= 9 && Math.abs(xVelocity) > 6) {
                    xVelocity -= 3;
                }
                if (Math.abs(yVelocity) <= 9 && Math.abs(yVelocity) > 6) {
                    yVelocity -= 3;
                }
                boolean right = (xBallCenter) - (yOtherPuckCenter) > 0;
                boolean up = (xOtherPuckCenter) - (yBallCenter) > 0;
                if (right && up) {
                    xVelocity = Math.abs(xVelocity) * 1;
                    yVelocity = Math.abs(yVelocity) * -1;
                }
                if (right && !up) {
                    xVelocity = Math.abs(xVelocity) * +1;
                    yVelocity = Math.abs(yVelocity) * +1;
                }
                if (!right && up) {
                    xVelocity = Math.abs(xVelocity) * -1;
                    yVelocity = Math.abs(yVelocity) * -1;
                }
                if (!right && !up) {
                    xVelocity = Math.abs(xVelocity) * -1;
                    yVelocity = Math.abs(yVelocity) * +1;
                }
                otherOnce = true;
            }
        }
        if (Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2) < 3) {
            xVelocity *= 1.5;
            yVelocity *= 1.5;
        }
    }

    public boolean collisionTwoCircle(double x, double y, double r, double X, double Y, double R) {
        boolean collision = false;
        if (Math.pow((x - X), 2) + Math.pow(y - Y, 2) <= Math.pow(r + R, 2)) {
            collision = true;
        }
        return collision;
    }

    public void checkWinner() {

        long seconds = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS) - pausedTime;
        if (timeLimitedMode && LimitTime == seconds) {
            System.out.println("TIME IS UP");
            timer.stop();
            if (myScore > otherScore) {
                WinnerFrame winnerFrame = new WinnerFrame(myPlayerName);
                GameHistoryFrame.addData(myPlayerName + "(winner)   " + myScore + " - " + otherScore + "   " + otherPlayerName);
            }
            if (otherScore > myScore) {
                WinnerFrame winnerFrame = new WinnerFrame(otherPlayerName);
                GameHistoryFrame.addData(myPlayerName + "   " + myScore + " - " + otherScore + "   " + otherPlayerName + "(winner)");
            }
            if (myScore == otherScore) {
                WinnerFrame winnerFrame = new WinnerFrame("no one");
                GameHistoryFrame.addData(myPlayerName + "   " + myScore + " - " + otherScore + "   " + otherPlayerName);

            }
        }
        if (goalLimitedMode) {
            if (myScore >= LimitGoal || otherScore >= LimitGoal) {
                if (!twoMarginMode) {
                    timer.stop();
                    if (myScore == LimitGoal) {
                        WinnerFrame winnerFrame = new WinnerFrame(myPlayerName);
                        GameHistoryFrame.addData(myPlayerName + "(winner)   " + myScore + " - " + otherScore + "   " + otherPlayerName);
                    } else {
                        WinnerFrame winnerFrame = new WinnerFrame(otherPlayerName);
                        GameHistoryFrame.addData(myPlayerName + "   " + myScore + " - " + otherScore + "   " + otherPlayerName + "(winner)");
                    }
                }
                if (twoMarginMode && Math.abs(myScore - otherScore) >= 2) {
                    timer.stop();
                    if (myScore > otherScore) {
                        WinnerFrame winnerFrame = new WinnerFrame(myPlayerName);
                        GameHistoryFrame.addData(myPlayerName + "(winner)   " + myScore + " - " + otherScore + "   " + otherPlayerName);
                    } else {
                        WinnerFrame winnerFrame = new WinnerFrame(otherPlayerName);
                        GameHistoryFrame.addData(myPlayerName + "   " + myScore + " - " + otherScore + "   " + otherPlayerName + "(winner)");
                    }
                }
            }
        }
    }

    public void checkGoal(double xBallCenter) {
        boolean online = false;

        if (xBallCenter + ballRadios < xMyMaxCritical && xBallCenter - ballRadios > xMyMinCritical && yBall <= deltaY) {
            online = true;
        }
        if (xBallCenter + ballRadios < xOtherMaxCritical && xBallCenter - ballRadios > xOtherMinCritical && yBall + ballSize >= height - deltaY) {
            online = true;
        }
        if (!online) {
            if (xBall + xVelocity <= deltaX || xBall + ballSize > width - deltaX) {
                if (mirrorWall) {
                    if (xBall + xVelocity <= deltaX) {
                        xBall = width - deltaX - ballSize;
                    }
                    if (xBall + ballSize > width - deltaX) {
                        xBall = deltaX;
                    }
                    xVelocity *= -1;
                }
                xVelocity *= -1;
            }
            if (yBall <= deltaY || yBall + ballSize > height - deltaY) {
                yVelocity *= -1;
            }
        }
        if (online && yBall < deltaY) {
            myScore++;
            goalAfter = true;
            xMyPuck = width / 2.0 - puckSize / 2.0;
            yMyPuck = (int) (height - puckSize - 1.5 * deltaY);
            xOtherPuck = width / 2.0 - puckSize / 2.0;
            yOtherPuck = puckSize / 2.0;
            xBall = width / 2.0 - ballSize / 2.0;
            yBall = height / 2.0 - ballSize / 2.0;
            mirrorWall = false;
            skipOtherCollision = false;
            skipMyCollision = false;
            repaint();
        } else if (online && yBall + ballRadios > height - deltaY) {
            otherScore++;
            goalAfter = true;
            xMyPuck = width / 2.0 - puckSize / 2.0;
            yMyPuck = (int) (height - puckSize - 1.5 * deltaY);
            xOtherPuck = width / 2.0 - puckSize / 2.0;
            yOtherPuck = puckSize / 2.0;
            xBall = width / 2.0 - ballSize / 2.0;
            yBall = height / 2.0 - ballSize / 2.0;
            mirrorWall = false;
            skipOtherCollision = false;
            skipMyCollision = false;
            repaint();
        }
    }

    public void AppearTheBonus() {
        bonusAppearance = true;
        bonusActivated = false;
        bonusRadios = 15;
        doneOnce = false;
        bonusAppearTime = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
        if (bonusPlaceIsSet) {
            bonusPlaceIsSet = false;
            xBonusCenter = Math.random() * (width - 2 * deltaX - 2 * bonusRadios - 50) + deltaX + bonusRadios + 50;
            yBonusCenter = Math.random() * (height - 2 * deltaY - 2 * bonusRadios - 50) + deltaY + bonusRadios + 50;
        }
        if (!typeSet) {
            typeSet = true;
            int type = (int) (3 * Math.random() + 1);
            bonusType = type;
        }
        bonusExistence();
    }

    public void FadeTheBonus() {
        bonusAppearance = false;
        bonusFirstExpand = false;
        bonusSecondExpand = false;
        typeSet = false;
        lastBonusFadeTime = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
    }

    public void ActiveTheBonus() {
        bonusActivated = true;
        goalAfter = false;
        FadeTheBonus();
    }

    public void bonusExistence() {
        long time = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
        if (time - bonusAppearTime > 5 && !bonusActivated && !bonusFirstExpand) {
            bonusFirstExpand = true;
            bonusRadios += 4;
        }
        if (time - bonusAppearTime > 10 && !bonusActivated && !bonusSecondExpand) {
            bonusSecondExpand = true;
            bonusRadios += 4;
        }
        if (time - bonusAppearTime >= 15 && bonusFirstExpand && bonusSecondExpand) {
            lastBonusFadeTime = now.until(ZonedDateTime.now(), ChronoUnit.SECONDS);
            FadeTheBonus();
        }
    }
}