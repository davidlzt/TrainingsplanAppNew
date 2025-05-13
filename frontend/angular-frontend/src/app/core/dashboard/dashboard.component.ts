import {AfterViewInit, ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {TrainingsplanService} from '../../services/trainingsplan.service/trainingsplan.service.component';
import {NgClass, NgForOf, NgIf} from '@angular/common';
import {MenuButtonComponent} from '../../shared/menu-button/menu-button.component';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    NgClass,
    NgForOf,
    MenuButtonComponent,
    NgIf,
    FormsModule
  ],
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, AfterViewInit {
  currentMonth: string | undefined;
  currentYear: number | undefined;
  daysInMonth: any[] = [];
  trainingCount: number = 5;
  lastTrainingDate: string = '2025-02-18';
  averageTrainingTime: number = 60;
  trainingName: string = '';
  trainingDescription: string = '';
  trainingPlans: any[] = [];
  daysOfWeek: string[] = ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"];
  trainingFrequency: number = 0;
  exercises: any[] = [];

  constructor(
    private router: Router,
    private cdr: ChangeDetectorRef,
    private trainingsplanService: TrainingsplanService,
  ) {}

  ngOnInit() {
    setTimeout(() => {
      const currentDate = new Date();
      this.currentYear = currentDate.getFullYear();
      this.currentMonth = currentDate.toLocaleString('default', { month: 'long' });
      this.generateCalendar();
      this.cdr.detectChanges();
      this.fetchTrainingPlans();
      const trainingsplanId = 1;
      this.loadTrainingDetails(trainingsplanId);
    });
  }

  ngAfterViewInit() {
    this.generateCalendar();
    this.cdr.detectChanges();
  }

  fetchTrainingPlans(): void {
    this.trainingsplanService.getAllTrainingPlans().subscribe(
      (data) => {
        console.log('Trainingspläne erhalten:', data);
        this.trainingPlans = data;
      },
      (error) => {
        console.error('Fehler beim Abrufen der Trainingspläne:', error);
      }
    );
  }
  formatTrainingDays(trainingDays: number[]): string {
    return trainingDays.map(day => this.daysOfWeek[day]).join(', ');
  }

  createTraining() {
    this.router.navigate(['/create-training'], {
      queryParams: {
        name: this.trainingName,
        description: this.trainingDescription
      }
    });
  }

  generateCalendar() {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);
    const firstDayOfWeek = firstDayOfMonth.getDay();

    const startDay = firstDayOfWeek === 0 ? 6 : firstDayOfWeek - 1;

    let days: any[] = [];

    for (let i = 0; i < startDay; i++) {
      days.push({ date: '', month: '', isToday: false });
    }

    for (let i = 1; i <= 30; i++) {
      const day = new Date(today.getFullYear(), today.getMonth(), i);
      days.push({
        date: day.getDate(),
        month: today.toLocaleString('default', { month: 'long' }),
        isToday: day.toDateString() === new Date().toDateString()
      });
    }

    this.daysInMonth = days;
  }

  dayHasTraining(day: any): boolean {
    if (!day.date || this.currentYear === undefined) return false;

    const currentDayOfWeek = new Date(this.currentYear, new Date().getMonth(), day.date).getDay();
    const rotatedDayOfWeek = (currentDayOfWeek === 0 ? 6 : currentDayOfWeek - 1);

    return this.trainingPlans.some(plan => plan.trainingDays.includes(rotatedDayOfWeek));
  }

  loadTrainingDetails(trainingsplanId: number) {
    this.trainingsplanService.getTrainingFrequency(trainingsplanId).subscribe((frequency: number) => {
      this.trainingFrequency = frequency;
    });

    this.trainingsplanService.getExercisesForPlan(trainingsplanId).subscribe((exercises: any[]) => {
      this.exercises = exercises;
    });
  }

  isTrainingDay(dayDate: number | undefined, trainingDays: number[]): boolean {
    if (typeof dayDate !== 'number' || isNaN(dayDate)) return false;
    if (this.currentYear === undefined) return false;

    const day = new Date(this.currentYear, new Date().getMonth(), dayDate);
    const dayOfWeek = day.getDay();
    const rotatedDayOfWeek = (dayOfWeek === 0 ? 6 : dayOfWeek - 1);

    return trainingDays.includes(rotatedDayOfWeek);
  }

  getRotatedWeekDays(): string[] {
    return this.daysOfWeek;
  }

  deleteTrainingPlan(planId: number): void {
    if (!confirm('Willst du diesen Trainingsplan wirklich löschen?')) return;

    this.trainingsplanService.deleteTrainingPlan(planId).subscribe(
      () => {
        console.log('Trainingsplan gelöscht:', planId);
        this.trainingPlans = this.trainingPlans.filter(plan => plan.id !== planId);
      },
      error => {
        console.error('Fehler beim Löschen des Trainingsplans:', error);
      }
    );
  }
  score: number = 0;
  gravity: number = 0.1;
  birdY: number = 200;
  velocity: number = 0;
  pipes: any[] = [];
  gameRunning: boolean = false;

  @ViewChild('gameCanvas', { static: false }) gameCanvas!: ElementRef<HTMLCanvasElement>;

  startGame() {
    this.resetGame();
    this.gameRunning = true;
    const ctx = this.gameCanvas.nativeElement.getContext('2d')!;
    const canvas = this.gameCanvas.nativeElement;

    let birdX = 30;
    let pipeWidth = 50;
    let gap = 150;

    const draw = () => {
      if (!this.gameRunning) return;

      ctx.clearRect(0, 0, canvas.width, canvas.height);

      this.velocity += this.gravity;
      this.birdY += this.velocity;

      ctx.fillStyle = "yellow";
      ctx.fillRect(birdX, this.birdY, 20, 20);

      if (this.pipes.length === 0 || this.pipes[this.pipes.length - 1].x < 150) {
        const topHeight = Math.floor(Math.random() * 200) + 20;
        this.pipes.push({ x: canvas.width, top: topHeight });
      }

      for (let i = 0; i < this.pipes.length; i++) {
        const pipe = this.pipes[i];
        pipe.x -= 1;

        ctx.fillStyle = "green";
        ctx.fillRect(pipe.x, 0, pipeWidth, pipe.top);
        ctx.fillRect(pipe.x, pipe.top + gap, pipeWidth, canvas.height - pipe.top - gap);

        if (
          birdX + 20 > pipe.x &&
          birdX < pipe.x + pipeWidth &&
          (this.birdY < pipe.top || this.birdY + 20 > pipe.top + gap)
        ) {
          this.stopGame();
        }

        if (pipe.x + pipeWidth < birdX && !pipe.passed) {
          this.score++;
          pipe.passed = true;
        }
      }

      if (this.birdY + 20 > canvas.height || this.birdY < 0) {
        this.stopGame();
      }

      requestAnimationFrame(draw);
    };

    document.addEventListener("keydown", this.flap);
    document.addEventListener("keyup", this.stopFlap);
    canvas.addEventListener("click", this.flap);

    draw();
  }

  flap = () => {
    if (this.gameRunning) {
      if (this.birdY > 0) {
        this.velocity = -3;
      }
    }
  };

  stopFlap = () => {
    if (this.gameRunning) {
      if (this.velocity < 0) {
        this.velocity = 0;
      }
    }
  };

  resetGame() {
    this.birdY = 200;
    this.velocity = 0;
    this.pipes = [];
    this.score = 0;
    this.gameRunning = true;
  }

  stopGame() {
    this.gameRunning = false;
    alert("Game Over! Deine Punkte: " + this.score);
    document.removeEventListener("keydown", this.flap);
    document.removeEventListener("keyup", this.stopFlap);
    this.gameCanvas.nativeElement.removeEventListener("click", this.flap);
  }

}
